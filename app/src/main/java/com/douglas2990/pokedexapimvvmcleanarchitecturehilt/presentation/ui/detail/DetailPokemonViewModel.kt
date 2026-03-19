package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetDetailPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailPokemonViewModel  @Inject constructor(
    private val detalhePokemonUseCase: GetDetailPokemonUseCase,
) :  ViewModel(){

    //val pokemonId: String
    private val _resultados = MutableLiveData<DetalhePokemon?>()

    val detalhePokemon: LiveData<DetalhePokemon?>
        get() = _resultados


    init {
        //recuperarPokemon("")
    }





   // fun recuperarPokemon(detalhePokemonId: String){
    //fun recuperarPokemon(pokemonId: String){
    fun recuperarPokemon(pokemonId: String){
        viewModelScope.launch {
            val detalhePokemon = detalhePokemonUseCase(pokemonId)
            _resultados.postValue( detalhePokemon )
        }
    }

}

