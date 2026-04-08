package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.gen_iii

import com.google.gson.annotations.SerializedName

data class PokemonSpeciesDetailGen3DTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("egg_groups")
    val eggGroups: List<EggGroupDTO>,
    @SerializedName("generation")
    val generation: GenerationInfoDTO
)

data class EggGroupDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class GenerationInfoDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
