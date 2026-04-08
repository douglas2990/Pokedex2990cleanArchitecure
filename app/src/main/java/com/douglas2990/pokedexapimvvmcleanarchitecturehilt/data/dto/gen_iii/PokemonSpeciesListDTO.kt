package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.gen_iii

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesListDTO(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<PokemonSpeciesItemDTO>
)

data class PokemonSpeciesItemDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
