package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.Resultado

data class Result(
    val name: String,
    val url: String
)

fun Result.toResult() :Resultado {
    return Resultado(
        nome = this.name,
        urlDaApi = this.url
    )
}