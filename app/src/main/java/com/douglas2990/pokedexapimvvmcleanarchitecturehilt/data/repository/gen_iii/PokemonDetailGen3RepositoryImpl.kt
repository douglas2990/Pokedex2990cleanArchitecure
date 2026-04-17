package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.repository.gen_iii

import android.util.Log
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.gen_iii.PokemonSpeciesDetailGen3DTO
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.remote.DummyAPI
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemon_detail.*
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.dao.gen_III.MoveGenIIIDao
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.entity.gen_III.MoveEntity
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.*
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.gen_iii.PokemonDetailGen3Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class PokemonDetailGen3RepositoryImpl @Inject constructor(
    private val pokemonAPI: DummyAPI,
    private val moveDao: MoveGenIIIDao
) : PokemonDetailGen3Repository {

    override suspend fun getPokemonDetailGen3(id: String): PokemonDetailGen3? = coroutineScope {
        try {
            val pokemonId = id.toInt()

            // 1. Chamadas em paralelo para dados básicos (Otimização de tempo)
            val pokemonDeferred = async { pokemonAPI.pokemonDetailComplete(id) }
            val speciesDeferred = async { pokemonAPI.pokemonSpecieGen3(id) }

            val response = pokemonDeferred.await()
            val speciesResponse = speciesDeferred.await()

            if (!response.isSuccessful || response.body() == null || !speciesResponse.isSuccessful) {
                return@coroutineScope null
            }

            val dto = response.body()!!
            val speciesDto = speciesResponse.body()!!

            // 2. Lógica de Cache Inteligente
            val cachedMoves = moveDao.getMovesByPokemon(pokemonId)

            val finalMoves = if (cachedMoves.isNotEmpty() && cachedMoves.none { it.type.isEmpty() }) {
                Log.i("REPO_GEN3", "🚀 Usando golpes do CACHE para o Pokémon $id")
                cachedMoves.map { it.toDomain() }
            } else {
                fetchMovesFromApi(pokemonId, dto)
            }

            assemblePokemonDetail(dto, speciesDto, finalMoves)

        } catch (e: Exception) {
            Log.e("REPO_GEN3", "❌ Erro fatal no Repository: ${e.message}")
            null
        }
    }

    /**
     * Busca os detalhes de cada golpe na API em paralelo.
     */
    private suspend fun fetchMovesFromApi(pokemonId: Int, dto: PokemonDetailDTO): List<MoveGenIIIDetail> = coroutineScope {
        Log.i("REPO_GEN3", "🌐 Buscando detalhes dos golpes na API...")

        val gbaVersionGroups = listOf("ruby-sapphire", "emerald", "firered-leafgreen")

        val apiMovesFiltered = dto.moves.filter { moveDto ->
            moveDto.versionGroupDetails.any { it.versionGroup.name in gbaVersionGroups }
        }

        val movesFromApi = apiMovesFiltered.map { moveDto ->
            async(Dispatchers.IO) {
                val gbaDetail = moveDto.versionGroupDetails.find { it.versionGroup.name in gbaVersionGroups }
                val moveName = moveDto.move.name

                val typeName = fetchMoveType(moveName)

                MoveGenIIIDetail(
                    name = moveName,
                    levelLearnedAt = gbaDetail?.levelLearnedAt ?: 0,
                    learnMethod = gbaDetail?.moveLearnMethod?.name ?: "level-up",
                    type = typeName
                )
            }
        }.awaitAll()

        // Persistência em background para não travar o retorno
        saveMovesToDb(pokemonId, movesFromApi)

        movesFromApi
    }

    /**
     * Resolve o tipo de um golpe específico com tratamento de erro isolado.
     */
    private suspend fun fetchMoveType(moveName: String): String {
        return try {
            val response = pokemonAPI.getMoveDetail(moveName)
            if (response.isSuccessful) {
                val type = response.body()?.type?.name ?: "normal"
                Log.d("POKEDEX_DEBUG", "✅ Golpe: $moveName -> $type")
                type
            } else {
                "normal"
            }
        } catch (e: Exception) {
            "normal"
        }
    }

    private suspend fun saveMovesToDb(pokemonId: Int, moves: List<MoveGenIIIDetail>) {
        val entities = moves.map {
            MoveEntity(
                moveName = it.name,
                pokemonId = pokemonId,
                levelLearnedAt = it.levelLearnedAt,
                learnMethod = it.learnMethod,
                type = it.type
            )
        }
        moveDao.insertMoves(entities)
    }

    private fun assemblePokemonDetail(
        dto: PokemonDetailDTO,
        speciesDto: PokemonSpeciesDetailGen3DTO,
        moves: List<MoveGenIIIDetail>
    ): PokemonDetailGen3 {
        // ... sua lógica de assemblePokemonDetail permanece a mesma, ela já está boa!
        // Apenas certifique-se de manter a ordenação que você já criou.
        /*val pastGen3Type = dto.pastTypes?.find { it.generation.name.contains("generation-iii") }
        val finalTypes = pastGen3Type?.types?.map { TypeGen3(it.type.name, it.type.url) }
            ?: dto.types.map { TypeGen3(it.type.name, it.type.url) }*/

/*        val finalTypes = if (!dto.pastTypes.isNullOrEmpty()) {
            // Procuramos o histórico que seja da Gen 3 ou o mais próximo DEPOIS dela
            // (Pois se ele era 'X' até a Gen 5, ele também era 'X' na Gen 3)
            val pastTypeForGen3 = dto.pastTypes.find { past ->
                val gen = past.generation.name.lowercase()
                gen.contains("generation-iii") ||
                        gen.contains("generation-iv") ||
                        gen.contains("generation-v")
            }

            // Se achou um histórico que englobe a Gen 3, usa ele.
            // Se não achou, usa o types atual (que é o caso do Magnemite, que já era Steel na Gen 3)
            pastTypeForGen3?.types?.map { TypeGen3(it.type.name, it.type.url) }
                ?: dto.types.map { TypeGen3(it.type.name, it.type.url) }
        } else {
            dto.types.map { TypeGen3(it.type.name, it.type.url) }
        }*/

        val finalTypes = if (!dto.pastTypes.isNullOrEmpty()) {
            // 1. Pegamos todos os históricos que aconteceram DA Geração 3 para frente
            // (Pois o que mudou na Gen 6 ou Gen 10 ainda reflete o que ele era na Gen 3)
            val historicosValidos = dto.pastTypes.filter { past ->
                val genNumber = extractGenNumber(past.generation.url)
                genNumber >= 3
            }

            // 2. Pegamos o histórico mais "antigo" dessa lista (o primeiro que mudou após a Gen 3)
            val alvo = historicosValidos.minByOrNull { extractGenNumber(it.generation.url) }

            alvo?.types?.map { TypeGen3(it.type.name, it.type.url) } ?: dto.types.map { TypeGen3(it.type.name, it.type.url) }
        } else {
            dto.types.map { TypeGen3(it.type.name, it.type.url) }
        }



        return PokemonDetailGen3(
            id = dto.id,
            nome = dto.name,
            sprites = assembleSprites(dto), // Podemos extrair os sprites para limpar o código
            tipos = finalTypes,
            status = dto.stats.map { PokemonStatGen3(it.stat.name, it.baseStat) },
            golpes = moves.sortedWith(compareBy({ it.levelLearnedAt }, { it.name })),
            eggGroups = speciesDto.eggGroups.map { it.name }
        )
    }

    // Função auxiliar para pegar o número da geração da URL: ".../generation/3/"
    private fun extractGenNumber(url: String): Int {
        return url.trimEnd('/').split('/').lastOrNull()?.toIntOrNull() ?: 99
    }

    // Função auxiliar apenas para organizar a bagunça dos sprites
    private fun assembleSprites(dto: PokemonDetailDTO) = PokemonSpritesGen3(
        frontDefault = dto.sprites.frontDefault,
        frontFemale = dto.sprites.frontFemale,
        frontShiny = dto.sprites.frontShiny,
        frontShinyFemale = dto.sprites.frontShinyFemale,
        other = OtherGen3(
            home = HomeGen3(dto.sprites.other?.home?.frontDefault, dto.sprites.other?.home?.frontShiny),
            officialArtwork = OfficialArtworkGen3(dto.sprites.other?.officialArtwork?.frontDefault)
        ),
        versions = VersionsGen3(
            generationIII = GenIIISprites(
                emerald = SpriteSimpleGen3(dto.sprites.frontDefault, dto.sprites.frontShiny),
                fireredLeafgreen = SpriteSimpleGen3(dto.sprites.frontDefault, dto.sprites.frontShiny),
                rubySapphire = SpriteSimpleGen3(dto.sprites.frontDefault, dto.sprites.frontShiny)
            )
        )
    )
}

// Extension function para converter Entity para Domain
fun MoveEntity.toDomain() = MoveGenIIIDetail(
    name = moveName,
    levelLearnedAt = levelLearnedAt,
    learnMethod = learnMethod,
    type = type
)