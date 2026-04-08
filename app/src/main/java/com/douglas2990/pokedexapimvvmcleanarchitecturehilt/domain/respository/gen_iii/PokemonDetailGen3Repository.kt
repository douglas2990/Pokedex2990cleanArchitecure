package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.gen_iii

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.PokemonDetailGen3

interface PokemonDetailGen3Repository {
    suspend fun getPokemonDetailGen3(id: String): PokemonDetailGen3?
}
