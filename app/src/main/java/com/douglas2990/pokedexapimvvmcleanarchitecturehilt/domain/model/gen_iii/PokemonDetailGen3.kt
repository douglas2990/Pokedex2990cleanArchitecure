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
    val eggGroups: List<String>
)

data class TypeGen3(
    val name: String,
    val url: String
)
