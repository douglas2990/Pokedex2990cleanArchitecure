package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.PokemonDetailGen3
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.gen_iii.GetPokemonDetailGen3UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Gen3PokemonDetailViewModel @Inject constructor(
    private val getPokemonDetailGen3UseCase: GetPokemonDetailGen3UseCase
) : ViewModel() {

    private val _pokemonDetail = MutableLiveData<PokemonDetailGen3?>()
    val pokemonDetail: LiveData<PokemonDetailGen3?> get() = _pokemonDetail

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun getPokemonDetail(id: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val detail = getPokemonDetailGen3UseCase(id)
                _pokemonDetail.value = detail
            } catch (e: Exception) {
                _pokemonDetail.value = null
            } finally {
                _loading.value = false
            }
        }
    }
}
