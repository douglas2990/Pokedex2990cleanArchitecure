package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.repository

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.dao.PokemonDao
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.PokemonEntity
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.PokemonFavorito
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.PokemonFavoritosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonFavoritosRepositoryImpl @Inject constructor(
    private val pokemonDao: PokemonDao
) : PokemonFavoritosRepository {

    override fun getFavoritos(): Flow<List<PokemonFavorito>> {
        return pokemonDao.getAllFavoritos().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun salvarFavorito(pokemon: PokemonFavorito) {
        pokemonDao.insertFavorito(pokemon.toEntity())
    }

    override suspend fun removerFavorito(pokemon: PokemonFavorito) {
        pokemonDao.deleteFavorito(pokemon.toEntity())
    }

    override suspend fun isFavorito(id: Int): Boolean {
        return pokemonDao.isFavorito(id)
    }

    private fun PokemonEntity.toDomain() = PokemonFavorito(id, nome, imageUrl, tipos)
    private fun PokemonFavorito.toEntity() = PokemonEntity(id, nome, imageUrl, tipos)
}
