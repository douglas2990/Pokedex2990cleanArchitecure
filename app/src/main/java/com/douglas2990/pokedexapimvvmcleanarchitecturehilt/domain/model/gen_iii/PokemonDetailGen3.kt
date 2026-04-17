package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii

import com.google.gson.annotations.SerializedName

data class PokemonDetailGen3(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val nome: String,
    @SerializedName("sprites")
    val sprites: PokemonSpritesGen3,
    @SerializedName("types")
    val tipos: List<TypeGen3>,
    @SerializedName("stats")
    val status: List<PokemonStatGen3>,
    @SerializedName("moves")
    val golpes: List<MoveGenIIIDetail>, // Certifique-se que o nome é EXATAMENTE 'golpes'
    @SerializedName("egg_groups")
    val eggGroups: List<String>,
    @SerializedName("past_types")
    val pastTypes: List<PastTypeDTO>? = emptyList()
)

data class TypeGen3(
    val name: String,
    val url: String
)

data class PastTypeDTO(
    @SerializedName("generation")
    val generation: GenerationDTO,
    @SerializedName("types")
    val types: List<TypeGen3>
)

data class GenerationDTO(
    @SerializedName("name")
    val name: String, // ex: "generation-v"
    @SerializedName("url")
    val url: String
)
