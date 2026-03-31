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
class ThirdFragmentViewModel @Inject constructor(
    private val getDetailPokemon1UseCase: GetDetailPokemon1UseCase
) : ViewModel() {

    private val _listaPokemon = MutableLiveData<List<DetalhePokemon1>>()
    val listaPokemon: LiveData<List<DetalhePokemon1>> get() = _listaPokemon

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private var fullList: List<DetalhePokemon1> = emptyList()

    private val typeTranslations = mapOf(
        "fogo" to "fire",
        "água" to "water",
        "agua" to "water",
        "planta" to "grass",
        "grama" to "grass",
        "elétrico" to "electric",
        "eletrico" to "electric",
        "gelo" to "ice",
        "lutador" to "fighting",
        "veneno" to "poison",
        "venenoso" to "poison",
        "terra" to "ground",
        "voador" to "flying",
        "psíquico" to "psychic",
        "psiquico" to "psychic",
        "inseto" to "bug",
        "pedra" to "rock",
        "rocha" to "rock",
        "fantasma" to "ghost",
        "dragão" to "dragon",
        "dragao" to "dragon",
        "sombrio" to "dark",
        "trevas" to "dark",
        "aço" to "steel",
        "aco" to "steel",
        "fada" to "fairy",
        "normal" to "normal"
    )

    init {
        recuperarResultado()
    }

    fun recuperarResultado() {
        _loading.value = true
        viewModelScope.launch {
            val result = getDetailPokemon1UseCase()
            fullList = result
            _listaPokemon.value = result
            _loading.value = false
        }
    }

    fun filterList(query: String?) {
        if (query.isNullOrBlank()) {
            _listaPokemon.value = fullList
            return
        }

        val lowerQuery = query.lowercase().trim()
        val translatedType = typeTranslations[lowerQuery] ?: lowerQuery

        val filteredList = fullList.filter { pokemon ->
            val nameMatch = pokemon.nome.lowercase().contains(lowerQuery)
            val idMatch = pokemon.id.toString() == lowerQuery
            val typeMatch = pokemon.tipos.any {
                it.type.name.lowercase().contains(lowerQuery) ||
                it.type.name.lowercase().contains(translatedType)
            }
            nameMatch || idMatch || typeMatch
        }
        _listaPokemon.value = filteredList
    }
}
