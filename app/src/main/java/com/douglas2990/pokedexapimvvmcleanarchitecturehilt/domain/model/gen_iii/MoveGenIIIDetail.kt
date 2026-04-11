package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii

import com.google.gson.annotations.SerializedName

data class MoveGenIIIDetail(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String = "", // Placeholder para o tipo
    @SerializedName("learn_method")
    val learnMethod: String,
    @SerializedName("level_learned_at")
    val levelLearnedAt: Int
)
