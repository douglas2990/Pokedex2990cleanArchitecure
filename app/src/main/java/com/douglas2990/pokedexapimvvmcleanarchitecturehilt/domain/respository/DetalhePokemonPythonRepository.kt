package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemonPython

interface DetalhePokemonPythonRepository {
    suspend fun recuperarPokemonsPython(): List<DetalhePokemonPython>
}
