package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.remote

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.PokemonList
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.DetailPokemon
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemonSpecies.PokemonSpecies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DummyAPI {

    @GET("pokemon")
    suspend fun getPokemon(
        @Query("limit")limit: Int,
        @Query("offset")offset: Int)
            : Response<PokemonList>

    @GET("pokemon/{id}")
    suspend fun pokemonDetail(
        @Path("id") id : String
    ): Response<DetailPokemon>

    @GET("pokemon-species/{id}")
    suspend fun pokemonSpecie(
        @Path("id") id : String
    ): Response<PokemonSpecies>
}