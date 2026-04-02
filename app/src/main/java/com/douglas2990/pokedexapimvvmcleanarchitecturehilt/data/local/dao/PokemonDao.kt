package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.dao

import androidx.room.*
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.PokemonCacheEntity
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.PokemonEntity
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.PokemonPythonCacheEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    // Favoritos
    @Query("SELECT * FROM pokemons_favoritos")
    fun getAllFavoritos(): Flow<List<PokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorito(pokemon: PokemonEntity)

    @Delete
    suspend fun deleteFavorito(pokemon: PokemonEntity)

    @Query("SELECT EXISTS(SELECT * FROM pokemons_favoritos WHERE id = :id)")
    suspend fun isFavorito(id: Int): Boolean

    // Cache para a Lista Completa (ThirdFragment)
    @Query("SELECT * FROM pokemons_cache")
    suspend fun getPokemonCache(): List<PokemonCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonCache(pokemons: List<PokemonCacheEntity>)

    @Query("DELETE FROM pokemons_cache")
    suspend fun clearPokemonCache()

    // Cache para DetalhePokemonPython
    @Query("SELECT * FROM pokemons_python_cache")
    suspend fun getPokemonPythonCache(): List<PokemonPythonCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonPythonCache(pokemons: List<PokemonPythonCacheEntity>)

    @Query("DELETE FROM pokemons_python_cache")
    suspend fun clearPokemonPythonCache()
}
