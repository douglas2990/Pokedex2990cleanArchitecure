package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon1
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.DetalhePokemon1Repository
import javax.inject.Inject

class GetDetailPokemon1UseCase@Inject() constructor(
    val detalhePokemon1Repository: DetalhePokemon1Repository
){
    suspend operator fun invoke() : List<DetalhePokemon1> {
        return try {
            //Regras de negócios
            detalhePokemon1Repository.recuperarPokemons()

        } catch (errorRecuperarUsuarios: Exception) {
            errorRecuperarUsuarios.printStackTrace()
            return emptyList()
        }
    }
}