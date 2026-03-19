package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetResultUseCase

class PokemonListViewModelFactory(
    private val resultadoUseCase: GetResultUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokemonListViewModel( resultadoUseCase ) as T
    }
}