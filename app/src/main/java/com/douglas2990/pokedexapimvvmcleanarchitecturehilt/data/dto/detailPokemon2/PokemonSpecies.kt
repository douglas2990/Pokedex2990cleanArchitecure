package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon2

import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    @SerializedName("egg_groups")
    val grupoOvos: List<EggGroupDTO>
)

data class EggGroupDTO(
    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String
)