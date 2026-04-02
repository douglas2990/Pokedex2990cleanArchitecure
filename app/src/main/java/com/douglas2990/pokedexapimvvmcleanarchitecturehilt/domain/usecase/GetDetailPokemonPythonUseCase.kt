package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemonPython
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.DetalhePokemonPythonRepository
import javax.inject.Inject

class GetDetailPokemonPythonUseCase @Inject constructor(
    private val repository: DetalhePokemonPythonRepository
) {
    suspend operator fun invoke(): List<DetalhePokemonPython> {
        return try {
            repository.recuperarPokemonsPython()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
