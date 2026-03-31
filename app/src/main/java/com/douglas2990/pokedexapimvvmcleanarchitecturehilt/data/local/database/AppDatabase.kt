package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.dao.PokemonDao
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
