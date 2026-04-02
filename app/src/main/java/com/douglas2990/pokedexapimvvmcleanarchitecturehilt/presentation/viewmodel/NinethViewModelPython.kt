package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaquo.python.Python
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemonPython
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetDetailPokemonPythonUseCase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NinethViewModelPython @Inject constructor(
    private val getDetailPokemonPythonUseCase: GetDetailPokemonPythonUseCase
) : ViewModel() {

    private val _pokemonResultados = MutableLiveData<List<DetalhePokemonPython>>()
    val pokemonResultados: LiveData<List<DetalhePokemonPython>> get() = _pokemonResultados

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private var isDataLoadedInPython = false

    init {
        // Carrega os dados para a memória do Python assim que a ViewModel é criada
        prepararDadosNoPython()
    }

    private fun prepararDadosNoPython() {
        viewModelScope.launch {
            val lista = getDetailPokemonPythonUseCase()
            val json = Gson().toJson(lista)
            
            withContext(Dispatchers.Default) {
                try {
                    val py = Python.getInstance()
                    val module = py.getModule("pokemon_search")
                    module.callAttr("load_data", json)
                    isDataLoadedInPython = true
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun showAll() {
        _loading.value = true
        viewModelScope.launch {
            val resultJson = withContext(Dispatchers.Default) {
                try {
                    val py = Python.getInstance()
                    val module = py.getModule("pokemon_search")
                    // Busca direto da memória global do Python
                    module.callAttr("get_all_cached").toString()
                } catch (e: Exception) {
                    "[]"
                }
            }
            emitResults(resultJson)
        }
    }

    fun searchWithPython(query: String) {
        _loading.value = true
        viewModelScope.launch {
            val resultJson = withContext(Dispatchers.Default) {
                try {
                    val py = Python.getInstance()
                    val module = py.getModule("pokemon_search")
                    // Agora o Python não recebe mais o JSON gigante, ele já tem na memória!
                    module.callAttr("process_request", query).toString()
                } catch (e: Exception) {
                    "[]"
                }
            }
            emitResults(resultJson)
        }
    }

    private fun emitResults(json: String) {
        try {
            val type = object : TypeToken<List<DetalhePokemonPython>>() {}.type
            val filteredList: List<DetalhePokemonPython> = Gson().fromJson(json, type)
            _pokemonResultados.postValue(filteredList)
        } catch (e: Exception) {
            _pokemonResultados.postValue(emptyList())
        }
        _loading.value = false
    }
}
