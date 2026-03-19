package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.pokemonEspecies.GrupoOvos
import com.google.gson.annotations.SerializedName

data class EggGroup(
    val name: String,
    val url: String
)

/*
fun EggGroup.toEggGroup(): GrupoOvos {
    return GrupoOvos(
        url = this.url,
        name = this.name
    )
}

 */





