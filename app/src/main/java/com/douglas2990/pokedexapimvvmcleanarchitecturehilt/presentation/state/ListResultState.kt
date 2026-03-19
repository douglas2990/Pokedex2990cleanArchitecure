package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.state

import android.support.annotation.StringRes
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.Result
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.Resultado

sealed class ListResultState {
    data class Success(val data: List<Result>) : ListResultState()
    data class SuccessResultado(val data: List<Resultado>) : ListResultState()
    data class Error(@StringRes val messageId: Int): ListResultState()
    object Loading : ListResultState()
}