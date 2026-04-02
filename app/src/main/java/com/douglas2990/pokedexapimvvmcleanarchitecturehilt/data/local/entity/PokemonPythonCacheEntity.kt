package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons_python_cache")
data class PokemonPythonCacheEntity(
    @PrimaryKey val id: Int,
    val nome: String,
    val espritesJson: String,
    val tiposJson: String,
    val statusJson: String
)
