package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.gen_iii

import com.google.gson.annotations.SerializedName

data class Generation3DTO(
    @SerializedName("abilities")
    val abilities: List<AbilityDTO>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main_region")
    val mainRegion: MainRegionDTO,
    @SerializedName("moves")
    val moves: List<MoveDTO>,
    @SerializedName("name")
    val name: String,
    @SerializedName("names")
    val names: List<NameDTO>,
    @SerializedName("pokemon_species")
    val pokemonSpecies: List<PokemonSpeciesDTO>,
    @SerializedName("types")
    val types: List<TypeDTO>,
    @SerializedName("version_groups")
    val versionGroups: List<VersionGroupDTO>
)

data class AbilityDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class MainRegionDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class MoveDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class NameDTO(
    @SerializedName("language")
    val language: LanguageDTO,
    @SerializedName("name")
    val name: String
)

data class LanguageDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class PokemonSpeciesDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class TypeDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class VersionGroupDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
