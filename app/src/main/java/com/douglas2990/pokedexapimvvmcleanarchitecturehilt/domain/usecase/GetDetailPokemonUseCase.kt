package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase


import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.DetalhePokemonRepository
import javax.inject.Inject


class GetDetailPokemonUseCase @Inject() constructor(
     val detalhePokemonRepository: DetalhePokemonRepository
){
    suspend operator fun invoke(pokemonId: String) : DetalhePokemon?{
    //suspend operator fun invoke() : DetalhePokemon?{
        return try {
            detalhePokemonRepository.detalhePokemon(pokemonId)
            //detalhePokemonRepository.detalhePokemon()
        }catch (erroRecuperarDetalhePokemon: Exception){
            erroRecuperarDetalhePokemon.printStackTrace()
            return null
        }
    }
}

