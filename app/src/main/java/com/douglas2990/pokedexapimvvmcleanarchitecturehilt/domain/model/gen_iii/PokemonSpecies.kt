package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii

import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
