package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon1
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetDetailPokemon1UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PokemonListDetailViewModel @Inject constructor(
    private val getDetailPokemon1UseCase: GetDetailPokemon1UseCase
) : ViewModel() {

    private val _resultados = MutableLiveData<List<DetalhePokemon1>>()

    //private val _resultadosDetail = MutableLiveData<DetalhePokemon1?>()

    val listaDetailPokemon: LiveData<List<DetalhePokemon1>>
        get() = _resultados


    init {
        recuperarResultado()
    }

    fun recuperarResultado(){
        viewModelScope.launch {
            val listaPokemon = getDetailPokemon1UseCase()
            _resultados.value = listaPokemon
        }
    }


}