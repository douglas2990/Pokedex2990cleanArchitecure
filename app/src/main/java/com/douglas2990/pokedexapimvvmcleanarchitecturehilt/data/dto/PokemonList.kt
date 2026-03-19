package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)