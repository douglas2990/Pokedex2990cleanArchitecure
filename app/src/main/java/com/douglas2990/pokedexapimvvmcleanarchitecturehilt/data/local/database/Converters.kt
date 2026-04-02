package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.database

import androidx.room.TypeConverter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Sprites
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.Type
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromSprites(sprites: Sprites): String = gson.toJson(sprites)

    @TypeConverter
    fun toSprites(json: String): Sprites = gson.fromJson(json, Sprites::class.java)

    @TypeConverter
    fun fromTypeList(types: List<Type>): String = gson.toJson(types)

    @TypeConverter
    fun toTypeList(json: String): List<Type> {
        val type = object : TypeToken<List<Type>>() {}.type
        return gson.fromJson(json, type)
    }
}
