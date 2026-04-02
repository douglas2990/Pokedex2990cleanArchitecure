package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.dao.PokemonDao
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.PokemonCacheEntity
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon1
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.DetalhePokemon1Repository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class GetPokemonRoomUseCase @Inject constructor(
    private val pokemonDao: PokemonDao,
    private val detalhePokemon1Repository: DetalhePokemon1Repository
) {
    private val gson = Gson()

    suspend operator fun invoke(): List<DetalhePokemon1> {
        // 1. Tenta pegar do banco
        val cache = pokemonDao.getPokemonCache()
        if (cache.isNotEmpty()) {
            return cache.map { it.toDomain() }
        }

        // 2. Se não tem, busca na API (via repositório original)
        val apiList = detalhePokemon1Repository.recuperarPokemons()
        
        // 3. Salva no banco para a próxima vez
        if (apiList.isNotEmpty()) {
            val entities = apiList.map { it.toEntity() }
            pokemonDao.insertPokemonCache(entities)
        }

        return apiList
    }

    private fun PokemonCacheEntity.toDomain(): DetalhePokemon1 {
        val typeList = object : TypeToken<List<com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Type>>() {}.type
        return DetalhePokemon1(
            id = id,
            nome = nome,
            esprites = gson.fromJson(imageUrl, com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Sprites::class.java),
            tipos = gson.fromJson(tipos, typeList)
        )
    }

    private fun DetalhePokemon1.toEntity() = PokemonCacheEntity(
        id = id,
        nome = nome,
        imageUrl = gson.toJson(esprites),
        tipos = gson.toJson(tipos)
    )
}
