package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.dao

import androidx.room.*
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM pokemons_favoritos")
    fun getAllFavoritos(): Flow<List<PokemonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorito(pokemon: PokemonEntity)

    @Delete
    suspend fun deleteFavorito(pokemon: PokemonEntity)

    @Query("SELECT EXISTS(SELECT * FROM pokemons_favoritos WHERE id = :id)")
    suspend fun isFavorito(id: Int): Boolean
}
