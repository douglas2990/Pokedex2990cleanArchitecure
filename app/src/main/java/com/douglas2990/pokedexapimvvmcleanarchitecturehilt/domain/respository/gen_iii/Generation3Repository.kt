package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.gen_iii

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.Generation3
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.PokemonSpecies

interface Generation3Repository {
    suspend fun getGeneration3Data(): Generation3?
    suspend fun getPokemonSpeciesList(offset: Int, limit: Int): List<PokemonSpecies>
}
