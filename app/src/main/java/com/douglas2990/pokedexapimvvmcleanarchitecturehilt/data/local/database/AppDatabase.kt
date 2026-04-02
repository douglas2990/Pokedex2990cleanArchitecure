package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.dao.PokemonDao
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.PokemonCacheEntity
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.PokemonEntity
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.PokemonPythonCacheEntity

@Database(
    entities = [
        PokemonEntity::class, 
        PokemonCacheEntity::class, 
        PokemonPythonCacheEntity::class
    ], 
    version = 3, 
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}
