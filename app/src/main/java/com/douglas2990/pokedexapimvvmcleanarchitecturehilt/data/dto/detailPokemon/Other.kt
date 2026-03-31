package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon

import com.google.gson.annotations.SerializedName

data class Other(
    val dream_world: DreamWorld,
    @SerializedName("home")
    val home: Home,
    @SerializedName("official-artwork")
    val official_artwork: OfficialArtwork,
    val showdown: Showdown
)