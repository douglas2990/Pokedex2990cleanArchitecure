package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.detailPokemon2

import com.google.gson.annotations.SerializedName

data class PokemonDetail(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val nome: String,

    @SerializedName("height")
    val altura: Int,

    @SerializedName("weight")
    val peso: Int,

    @SerializedName("sprites")
    val esprites: SpritesDTO,

    @SerializedName("types")
    val tipos: List<TypeSlotDTO>,

    @SerializedName("stats")
    val status: List<StatSlotDTO>,

    @SerializedName("moves")
    val movimentos: List<MoveSlotDTO>
)

data class SpritesDTO(
    @SerializedName("front_default")
    val front_default: String,@SerializedName("other")
    val other: OtherSpritesDTO
)

data class OtherSpritesDTO(
    @SerializedName("home")
    val home: HomeDTO,

    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkDTO
)

data class HomeDTO(
    @SerializedName("front_default")
    val front_default: String
)

data class OfficialArtworkDTO(
    @SerializedName("front_default")
    val front_default: String
)

data class TypeSlotDTO(
    @SerializedName("slot")
    val slot: Int,

    @SerializedName("type")
    val type: TypeDTO
)

data class TypeDTO(
    @SerializedName("name")
    val name: String
)

data class StatSlotDTO(
    @SerializedName("base_stat")
    val base_stat: Int,

    @SerializedName("effort")
    val effort: Int,

    @SerializedName("stat")
    val stat: StatDTO
)

data class StatDTO(
    @SerializedName("name")
    val name: String
)

data class MoveSlotDTO(
    @SerializedName("move")
    val move: MoveDTO
    // Opcional: Se quiser pegar o nível em que aprende, teria que mapear "version_group_details" aqui
)

data class MoveDTO(
    @SerializedName("name")
    val name: String,

    @SerializedName("url")
    val url: String
)