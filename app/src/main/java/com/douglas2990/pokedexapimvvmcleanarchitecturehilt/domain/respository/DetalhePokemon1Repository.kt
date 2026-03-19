package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon1

interface DetalhePokemon1Repository {
    suspend fun recuperarPokemons(): List<DetalhePokemon1>
   // suspend fun recuperarDetalhesPokemon(id: String): DetalhePokemon1?
}