package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.Resultado
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetResultUseCase
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.state.ListResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val resultadoUseCase: GetResultUseCase
) : ViewModel() {

    private val _resultados = MutableLiveData<List<Resultado>>()

    val listaPokemon: LiveData<List<Resultado>>
        get() = _resultados

    init {
        recuperarResultado()
    }

    fun recuperarResultado(){
        viewModelScope.launch {
            val listaPokemon = resultadoUseCase()
            _resultados.postValue( listaPokemon )
        }
    }

    val _resultadosPokemon : MutableLiveData<ListResultState> by lazy{
        MutableLiveData<ListResultState>()
    }

    val state: MutableLiveData<ListResultState> by lazy{
        MutableLiveData<ListResultState>()
    }

    val listaPokemonResultado: MutableLiveData<ListResultState> by lazy{
        MutableLiveData<ListResultState>()
    }

}