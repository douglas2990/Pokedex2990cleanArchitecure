package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.dao.gen_III

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.gen_III.MoveEntity

@Dao
interface MoveGenIIIDao {

    @Query("SELECT * FROM pokemon_moves_genIII WHERE pokemonId = :pokemonId ORDER BY levelLearnedAt ASC")
    suspend fun getMovesByPokemon(pokemonId: Int): List<MoveEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoves(moves: List<MoveEntity>)

    @Query("DELETE FROM pokemon_moves_genIII WHERE pokemonId = :pokemonId")
    suspend fun deleteMovesByPokemon(pokemonId: Int)


    @Query("SELECT * FROM pokemon_moves_genIII WHERE moveName = :name AND pokemonId = :pokemonId LIMIT 1")
    suspend fun getMoveByNameAndPokemon(name: String, pokemonId: Int): MoveEntity?

    @Query("UPDATE pokemon_moves_genIII SET type = :type WHERE moveName = :moveName")
    suspend fun updateMoveType(moveName: String, type: String)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMove(move: MoveEntity)
}