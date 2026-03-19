package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.pokemonEspecies.PokemonEspecies
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.PokemonSpecieRepository
import javax.inject.Inject

class GetPokemonSpecieUseCase @Inject() constructor(
    val pokemonSpecieRepository: PokemonSpecieRepository
){
    suspend operator fun invoke(pokemonId: String): PokemonEspecies?{
        return try {
            pokemonSpecieRepository.pokemonSpecie(pokemonId)
        }catch (erroRecuperarDetalhePokemon: Exception){
            erroRecuperarDetalhePokemon.printStackTrace()
            return null
        }
    }
}