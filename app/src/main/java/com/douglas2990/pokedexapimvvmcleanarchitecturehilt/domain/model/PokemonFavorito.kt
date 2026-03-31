package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model

data class PokemonFavorito(
    val id: Int,
    val nome: String,
    val imageUrl: String,
    val tipos: String // Vamos salvar como String separada por vírgula ou JSON para facilitar
)
