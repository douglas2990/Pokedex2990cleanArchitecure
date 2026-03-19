package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)