package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.repository

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.toPokemonSpecies
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.remote.DummyAPI
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.pokemonEspecies.PokemonEspecies
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.PokemonSpecieRepository
import javax.inject.Inject

class PokemonSpecieRepositoryImpl @Inject constructor(
    val dummyAPI: DummyAPI
): PokemonSpecieRepository {
    override suspend fun pokemonSpecie(pokemonId: String): PokemonEspecies? {
        try{
            val respostaPokemonSpecie = dummyAPI.pokemonSpecie(pokemonId)
            if( respostaPokemonSpecie.isSuccessful && respostaPokemonSpecie.body() != null){
                val pokemonSpecieAPIDTO = respostaPokemonSpecie.body()
                val pokemonSpecies = pokemonSpecieAPIDTO

                if ( pokemonSpecies != null){

                    return pokemonSpecies.toPokemonSpecies()
                }
            }
        } catch (erroRecuperarPokemonSpecie: Exception){
            erroRecuperarPokemonSpecie.printStackTrace()
        }
        return null
    }

}