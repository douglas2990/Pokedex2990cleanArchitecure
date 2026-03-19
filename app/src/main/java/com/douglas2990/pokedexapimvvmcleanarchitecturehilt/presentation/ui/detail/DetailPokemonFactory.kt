package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetDetailPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel

class DetailPokemonFactory (val pokemonId: String) : ViewModelProvider.Factory {
    val getUseCasepokemon: GetDetailPokemonUseCase? = null
    val detailPokemonViewModel =  DetailPokemonViewModel(getUseCasepokemon!!)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        ///return DetailPokemonViewModel(getUseCasepokemon!!, pokemonId = pokemonId ) as T
        return detailPokemonViewModel.recuperarPokemon(pokemonId) as T
    }

}

