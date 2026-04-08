package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.pokemon_detail

import com.google.gson.annotations.SerializedName

data class PokemonDetailDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("base_experience")
    val baseExperience: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("order")
    val order: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("abilities")
    val abilities: List<AbilityDTO>,
    @SerializedName("forms")
    val forms: List<NamedResourceDTO>,
    @SerializedName("game_indices")
    val gameIndices: List<GameIndexDTO>,
    @SerializedName("held_items")
    val heldItems: List<HeldItemDTO>,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    @SerializedName("moves")
    val moves: List<MoveDTO>,
    @SerializedName("species")
    val species: NamedResourceDTO,
    @SerializedName("sprites")
    val sprites: SpritesDTO,
    @SerializedName("stats")
    val stats: List<StatDTO>,
    @SerializedName("types")
    val types: List<TypeDTO>,
    @SerializedName("past_types")
    val pastTypes: List<PastTypeDTO>
)

data class AbilityDTO(
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("ability")
    val ability: NamedResourceDTO
)

data class NamedResourceDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class GameIndexDTO(
    @SerializedName("game_index")
    val gameIndex: Int,
    @SerializedName("version")
    val version: NamedResourceDTO
)

data class HeldItemDTO(
    @SerializedName("item")
    val item: NamedResourceDTO,
    @SerializedName("version_details")
    val versionDetails: List<VersionDetailDTO>
)

data class VersionDetailDTO(
    @SerializedName("rarity")
    val rarity: Int,
    @SerializedName("version")
    val version: NamedResourceDTO
)

data class MoveDTO(
    @SerializedName("move")
    val move: NamedResourceDTO,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<MoveVersionDetailDTO>
)

data class MoveVersionDetailDTO(
    @SerializedName("level_learned_at")
    val levelLearnedAt: Int,
    @SerializedName("version_group")
    val versionGroup: NamedResourceDTO,
    @SerializedName("move_learn_method")
    val moveLearnMethod: NamedResourceDTO
)

data class StatDTO(
    @SerializedName("base_stat")
    val baseStat: Int,
    @SerializedName("effort")
    val effort: Int,
    @SerializedName("stat")
    val stat: NamedResourceDTO
)

data class TypeDTO(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: NamedResourceDTO
)

data class PastTypeDTO(
    @SerializedName("generation")
    val generation: NamedResourceDTO,
    @SerializedName("types")
    val types: List<TypeDTO>
)

data class SpritesDTO(
    @SerializedName("back_default")
    val backDefault: String?,
    @SerializedName("back_female")
    val backFemale: String?,
    @SerializedName("back_shiny")
    val backShiny: String?,
    @SerializedName("back_shiny_female")
    val backShinyFemale: String?,
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_female")
    val frontFemale: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: String?,
    @SerializedName("other")
    val other: OtherDTO?
)

data class OtherDTO(
    @SerializedName("dream_world")
    val dreamWorld: DreamWorldDTO?,
    @SerializedName("home")
    val home: HomeDTO?,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkDTO?
)

data class DreamWorldDTO(
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_female")
    val frontFemale: String?
)

data class HomeDTO(
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_female")
    val frontFemale: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: String?
)

data class OfficialArtworkDTO(
    @SerializedName("front_default")
    val frontDefault: String?
)
