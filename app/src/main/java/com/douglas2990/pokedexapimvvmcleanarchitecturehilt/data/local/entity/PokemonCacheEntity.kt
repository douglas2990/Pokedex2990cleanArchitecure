package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons_cache")
data class PokemonCacheEntity(
    @PrimaryKey val id: Int,
    val nome: String,
    val imageUrl: String,
    val tipos: String // Salvaremos os tipos como String separada por vírgula
)
