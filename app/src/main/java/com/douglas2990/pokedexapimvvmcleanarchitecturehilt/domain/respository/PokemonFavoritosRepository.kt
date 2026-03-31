package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.PokemonFavorito
import kotlinx.coroutines.flow.Flow

interface PokemonFavoritosRepository {
    fun getFavoritos(): Flow<List<PokemonFavorito>>
    suspend fun salvarFavorito(pokemon: PokemonFavorito)
    suspend fun removerFavorito(pokemon: PokemonFavorito)
    suspend fun isFavorito(id: Int): Boolean
}
