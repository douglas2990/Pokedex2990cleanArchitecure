package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.gen_III

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_moves_genIII")
data class MoveEntity(
    @PrimaryKey val moveName: String,
    val pokemonId: Int,       // Para saber de qual Pokémon é esse golpe
    val levelLearnedAt: Int,
    val learnMethod: String,
    val type: String          // Aqui salvamos o tipo que veio da API ou do Mapper
)