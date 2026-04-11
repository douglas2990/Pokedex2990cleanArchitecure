package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.repository.gen_iii

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.remote.DummyAPI
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.Generation3
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.MoveGenIII
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.MoveGenIIIDetail
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.PokemonSpecies
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.gen_iii.Generation3Repository
import javax.inject.Inject

class Generation3RepositoryImpl @Inject constructor(
    private val pokemonAPI: DummyAPI
) : Generation3Repository {

    override suspend fun getGeneration3Data(): Generation3? {
        return try {
            val genResponse = pokemonAPI.getGeneration3()
            val speciesResponse = pokemonAPI.getPokemonSpeciesList(0, 386)
            // Novo endpoint para pegar a lista completa de moves (0 a 354)
            val movesResponse = pokemonAPI.getMoveList(0, 354)

            if (genResponse.isSuccessful && speciesResponse.isSuccessful && movesResponse.isSuccessful) {
                val genDto = genResponse.body()!!
                val speciesDto = speciesResponse.body()!!
                val movesDto = movesResponse.body()!!

                Generation3(
                    id = genDto.id,
                    name = genDto.name,
                    mainRegion = genDto.mainRegion.name,
                    pokemonCount = speciesDto.results.size,
                    moveCount = movesDto.results.size,
                    abilityCount = genDto.abilities.size,
                    pokemonSpecies = speciesDto.results.map {
                        PokemonSpecies(name = it.name, url = it.url)
                    }.sortedBy { extractId(it.url) },
                    moves = movesDto.results.map {
                        //MoveGenIII(name = it.name, url = it.url)
                        MoveGenIIIDetail(
                            name = it.name,
                            type = "",             // Valor padrão para listagem geral
                            learnMethod = "N/A",   // Valor padrão para listagem geral
                            levelLearnedAt = 0     // Valor padrão para listagem geral
                        )
                    }
                )
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun getPokemonSpeciesList(offset: Int, limit: Int): List<PokemonSpecies> {
        return try {
            val response = pokemonAPI.getPokemonSpeciesList(offset, limit)
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.results.map {
                    PokemonSpecies(name = it.name, url = it.url)
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    private fun extractId(url: String): Int {
        return url.split("/").filter { it.isNotEmpty() }.last().toInt()
    }
}
