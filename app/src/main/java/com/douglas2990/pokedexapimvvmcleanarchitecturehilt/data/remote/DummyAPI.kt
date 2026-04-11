package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.remote

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.PokemonList
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon.DetailPokemon
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.gen_iii.Generation3DTO
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.gen_iii.MoveDetailDTO
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.gen_iii.MoveListDTO
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.gen_iii.PokemonSpeciesDetailGen3DTO
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.gen_iii.PokemonSpeciesListDTO
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemon_detail.PokemonDetailDTO
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

    @GET("pokemon/{id}")
    suspend fun pokemonDetailComplete(
        @Path("id") id : String
    ): Response<PokemonDetailDTO>

    @GET("pokemon-species/{id}")
    suspend fun pokemonSpecie(
        @Path("id") id : String
    ): Response<PokemonSpecies>

    @GET("pokemon-species/{id}")
    suspend fun pokemonSpecieGen3(
        @Path("id") id : String
    ): Response<PokemonSpeciesDetailGen3DTO>

    @GET("generation/3")
    suspend fun getGeneration3(): Response<Generation3DTO>

    @GET("pokemon-species")
    suspend fun getPokemonSpeciesList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<PokemonSpeciesListDTO>

    @GET("move")
    suspend fun getMoveList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<MoveListDTO>

    @GET("move/{name}")
    suspend fun getMoveDetail(
        @Path("name") name: String
    ): Response<MoveDetailDTO>


}
