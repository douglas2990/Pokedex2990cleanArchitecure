package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.repository.gen_iii

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.remote.DummyAPI
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemon_detail.*
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.*
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.gen_iii.PokemonDetailGen3Repository
import javax.inject.Inject

class PokemonDetailGen3RepositoryImpl @Inject constructor(
    private val pokemonAPI: DummyAPI
) : PokemonDetailGen3Repository {

    override suspend fun getPokemonDetailGen3(id: String): PokemonDetailGen3? {
        return try {
            val response = pokemonAPI.pokemonDetailComplete(id)
            val speciesResponse = pokemonAPI.pokemonSpecieGen3(id)
            
            if (response.isSuccessful && response.body() != null && speciesResponse.isSuccessful && speciesResponse.body() != null) {
                val dto = response.body()!!
                val speciesDto = speciesResponse.body()!!

                // Lógica de Ouro: Buscar tipos históricos da Geração 3
                val pastGen3Type = dto.pastTypes.find { past ->
                    past.generation.name.contains("generation-v") || 
                    past.generation.name.contains("generation-iii")
                }

                val finalTypes = if (pastGen3Type != null) {
                    pastGen3Type.types.map { TypeGen3(name = it.type.name, url = it.type.url) }
                } else {
                    dto.types.map { TypeGen3(name = it.type.name, url = it.type.url) }
                }

                PokemonDetailGen3(
                    id = dto.id,
                    nome = dto.name,
                    sprites = PokemonSpritesGen3(
                        frontDefault = dto.sprites.frontDefault,
                        frontFemale = dto.sprites.frontFemale,
                        frontShiny = dto.sprites.frontShiny,
                        frontShinyFemale = dto.sprites.frontShinyFemale,
                        other = OtherGen3(
                            home = HomeGen3(
                                frontDefault = dto.sprites.other?.home?.frontDefault,
                                frontShiny = dto.sprites.other?.home?.frontShiny
                            ),
                            officialArtwork = OfficialArtworkGen3(
                                frontDefault = dto.sprites.other?.officialArtwork?.frontDefault
                            )
                        ),
                        versions = VersionsGen3(
                            generationIII = GenIIISprites(
                                emerald = SpriteSimpleGen3(
                                    frontDefault = dto.sprites.other?.home?.frontDefault,
                                    frontShiny = dto.sprites.other?.home?.frontShiny
                                ),
                                fireredLeafgreen = SpriteSimpleGen3(
                                    frontDefault = dto.sprites.frontDefault,
                                    frontShiny = dto.sprites.frontShiny
                                ),
                                rubySapphire = SpriteSimpleGen3(
                                    frontDefault = dto.sprites.frontDefault,
                                    frontShiny = dto.sprites.frontShiny
                                )
                            )
                        )
                    ),
                    tipos = finalTypes,
                    status = dto.stats.map { 
                        PokemonStatGen3(name = it.stat.name, baseStat = it.baseStat)
                    },
                    golpes = dto.moves.map { it.move.name },
                    eggGroups = speciesDto.eggGroups.map { it.name }
                )
            } else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
