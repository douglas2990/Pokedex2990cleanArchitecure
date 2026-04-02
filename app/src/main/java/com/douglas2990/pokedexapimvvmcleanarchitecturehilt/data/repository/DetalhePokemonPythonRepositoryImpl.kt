package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.repository

import android.util.Log
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.dao.PokemonDao
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.PokemonPythonCacheEntity
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.remote.DummyAPI
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemonPython
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.DetalhePokemonPythonRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class DetalhePokemonPythonRepositoryImpl @Inject constructor(
    private val pokemonAPI: DummyAPI,
    private val pokemonDao: PokemonDao
) : DetalhePokemonPythonRepository {

    private val gson = Gson()

    override suspend fun recuperarPokemonsPython(): List<DetalhePokemonPython> {
        try {
            // 1. Tenta carregar do Cache primeiro
            val cache = pokemonDao.getPokemonPythonCache()
            if (cache.isNotEmpty()) {
                Log.i("PokemonPythonCache", "Carregando cache Python do Banco de Dados")
                return cache.map { it.toDomain() }
            }

            // 2. Se vazio, busca na API
            Log.i("PokemonPythonCache", "Cache vazio, baixando dados da PokéAPI...")
            val resposta = pokemonAPI.getPokemon(151, 0) 

            if (resposta.isSuccessful && resposta.body() != null) {
                val listResult = resposta.body()?.results
                if (listResult != null) {
                    val pokemonList = listResult.map { result ->
                        val respostaPokemonDetail = pokemonAPI.pokemonDetail(result.name)
                        val pokemonDetail = respostaPokemonDetail.body()!!

                        DetalhePokemonPython(
                            id = pokemonDetail.id,
                            nome = pokemonDetail.name,
                            esprites = pokemonDetail.sprites,
                            tipos = pokemonDetail.types,
                            status = pokemonDetail.stats
                        )
                    }

                    // 3. Salva no Cache
                    val entities = pokemonList.map { it.toEntity() }
                    pokemonDao.insertPokemonPythonCache(entities)

                    return pokemonList
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return emptyList()
    }

    private fun DetalhePokemonPython.toEntity() = PokemonPythonCacheEntity(
        id = id,
        nome = nome,
        espritesJson = gson.toJson(esprites),
        tiposJson = gson.toJson(tipos),
        statusJson = gson.toJson(status)
    )

    private fun PokemonPythonCacheEntity.toDomain(): DetalhePokemonPython {
        val typeList = object : TypeToken<List<com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Type>>() {}.type
        val statList = object : TypeToken<List<com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Stat>>() {}.type
        
        return DetalhePokemonPython(
            id = id,
            nome = nome,
            esprites = gson.fromJson(espritesJson, com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Sprites::class.java),
            tipos = gson.fromJson(tiposJson, typeList),
            status = gson.fromJson(statusJson, statList)
        )
    }
}
