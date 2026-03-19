package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.Resultado

interface ResultRepository {
    suspend fun recuperarResult() : List<Resultado>
}