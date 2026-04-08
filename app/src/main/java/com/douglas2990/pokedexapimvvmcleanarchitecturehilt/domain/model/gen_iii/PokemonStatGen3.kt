package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii

import com.google.gson.annotations.SerializedName

data class PokemonStatGen3(
    @SerializedName("name")
    val name: String,
    @SerializedName("base_stat")
    val baseStat: Int
)
