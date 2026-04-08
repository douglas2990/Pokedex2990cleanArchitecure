package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.gen_iii

import com.google.gson.annotations.SerializedName

data class MoveListDTO(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<MoveItemDTO>
)

data class MoveItemDTO(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
