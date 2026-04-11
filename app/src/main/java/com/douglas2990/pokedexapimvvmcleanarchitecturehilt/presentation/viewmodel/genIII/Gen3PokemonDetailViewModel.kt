package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.genIII

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.PokemonDetailGen3
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.gen_iii.GetPokemonDetailGen3UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
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
        // Evita chamadas duplicadas se o ID for o mesmo
        if (_pokemonDetail.value?.id?.toString() == id) return

        _loading.value = true
        viewModelScope.launch {
            try {
                // Como seu Repository usa awaitAll(), o 'detail' já vem completo da API
                val detail = getPokemonDetailGen3UseCase(id)

                if (detail != null) {
                    _pokemonDetail.value = detail
                    Log.d("VIEWMODEL_GEN3", "Pokémon carregado: ${detail.nome} com ${detail.golpes.size} golpes.")
                } else {
                    _pokemonDetail.value = null
                }
            } catch (e: Exception) {
                Log.e("VIEWMODEL_GEN3", "Erro ao carregar: ${e.message}")
                _pokemonDetail.value = null
            } finally {
                _loading.value = false
            }
        }
    }

}
