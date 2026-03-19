package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon1
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.pokemonEspecies.GrupoOvos
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.pokemonEspecies.PokemonEspecies
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetDetailPokemon1UseCase
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetPokemonSpecieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PokemonSpeciesViewModel @Inject constructor(
    private val getPokemonSpecieUseCase: GetPokemonSpecieUseCase
) : ViewModel() {

    private val _resultados = MutableLiveData<PokemonEspecies?>()

    val detalhePokemon: LiveData<PokemonEspecies?>
        get() = _resultados




    init{

    }




    fun recuperarPokemon(pokemonId: String){
        viewModelScope.launch {
            val detalhePokemon = getPokemonSpecieUseCase(pokemonId)
            _resultados.postValue( detalhePokemon )
        }
    }

    fun recuperarEggGroupPokemon(pokemonId: String){
        viewModelScope.launch {
            val detalhePokemon = getPokemonSpecieUseCase(pokemonId)
            _resultados.postValue( detalhePokemon )
        }
    }



}