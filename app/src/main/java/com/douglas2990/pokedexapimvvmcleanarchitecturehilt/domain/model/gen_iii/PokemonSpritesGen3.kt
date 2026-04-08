package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii

import com.google.gson.annotations.SerializedName

data class PokemonSpritesGen3(
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_female")
    val frontFemale: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: String?,
    @SerializedName("other")
    val other: OtherGen3?,
    @SerializedName("versions")
    val versions: VersionsGen3?
)

data class OtherGen3(
    @SerializedName("home")
    val home: HomeGen3?,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtworkGen3?
)

data class HomeGen3(
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?
)

data class OfficialArtworkGen3(
    @SerializedName("front_default")
    val frontDefault: String?
)

data class VersionsGen3(
    @SerializedName("generation-iii")
    val generationIII: GenIIISprites?
)

data class GenIIISprites(
    @SerializedName("emerald")
    val emerald: SpriteSimpleGen3?,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: SpriteSimpleGen3?,
    @SerializedName("ruby-sapphire")
    val rubySapphire: SpriteSimpleGen3?
)

data class SpriteSimpleGen3(
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?
)
