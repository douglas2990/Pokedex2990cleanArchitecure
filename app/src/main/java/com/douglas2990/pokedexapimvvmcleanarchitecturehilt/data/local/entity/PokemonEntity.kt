package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons_favoritos")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val nome: String,
    val imageUrl: String,
    val tipos: String
)
