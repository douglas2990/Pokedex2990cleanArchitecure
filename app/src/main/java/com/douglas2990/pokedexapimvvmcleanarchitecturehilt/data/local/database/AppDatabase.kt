package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.dao.PokemonDao
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.dao.gen_III.MoveGenIIIDao
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.PokemonCacheEntity
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.PokemonEntity
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.PokemonPythonCacheEntity
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.gen_III.MoveEntity

@Database(
    entities = [
        PokemonEntity::class,
        PokemonCacheEntity::class,
        PokemonPythonCacheEntity::class,
        MoveEntity::class // 1. Adicionada a nova entidade de golpes
    ],
    version = 5, // 2. Incrementada a versão (era 3, agora 4)
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    // 3. Adicionado o acesso ao DAO de golpes
    abstract fun moveGenIIIDao(): MoveGenIIIDao
}
