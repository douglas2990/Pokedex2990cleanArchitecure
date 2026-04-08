package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii

import com.google.gson.annotations.SerializedName

data class Generation3(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("main_region")
    val mainRegion: String,
    @SerializedName("pokemon_count")
    val pokemonCount: Int,
    @SerializedName("move_count")
    val moveCount: Int,
    @SerializedName("ability_count")
    val abilityCount: Int,
    @SerializedName("pokemon_species")
    val pokemonSpecies: List<PokemonSpecies>,
    @SerializedName("moves")
    val moves: List<MoveGenIII>
)
