package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.Resultado
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.ResultRepository
import javax.inject.Inject

class GetResultUseCase @Inject() constructor(
    private val resultRepository: ResultRepository
){

    suspend operator fun invoke() : List<Resultado> {
        return try {
            //Regras de negócios
            resultRepository.recuperarResult()

        } catch (errorRecuperarUsuarios: Exception) {
            errorRecuperarUsuarios.printStackTrace()
            return emptyList()
        }
    }
}