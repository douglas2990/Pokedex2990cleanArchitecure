package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.gen_iii

import com.google.gson.annotations.SerializedName

data class MoveDetailDTO(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("accuracy")
    val accuracy: Int?,
    @SerializedName("power")
    val power: Int?,
    @SerializedName("pp")
    val pp: Int?,
    @SerializedName("priority")
    val priority: Int,
    @SerializedName("type")
    val type: MoveTypeDTO,
    @SerializedName("damage_class")
    val damageClass: DamageClassDTO?
)

data class MoveTypeDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

data class DamageClassDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)