package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaquo.python.Python
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.detalhe.DetalhePokemon1
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetDetailPokemon1UseCase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NinethViewModel @Inject constructor(
    private val getDetailPokemon1UseCase: GetDetailPokemon1UseCase
) : ViewModel() {

    private val _pokemonResultados = MutableLiveData<List<DetalhePokemon1>>()
    val pokemonResultados: LiveData<List<DetalhePokemon1>> get() = _pokemonResultados

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private var fullListJson: String = ""

    init {
        prepararDados()
    }

    private fun prepararDados() {
        viewModelScope.launch {
            val lista = getDetailPokemon1UseCase()
            fullListJson = Gson().toJson(lista)
        }
    }

    fun searchWithPython(query: String) {
        _loading.value = true
        viewModelScope.launch {
            val resultadoJson = withContext(Dispatchers.Default) {
                try {
                    val py = Python.getInstance()
                    val pyFile = py.getModule("pokemon_search")
                    pyFile.callAttr("search_pokemon", query, fullListJson).toString()
                } catch (e: Exception) {
                    "[]"
                }
            }
            
            val type = object : TypeToken<List<Map<String, Any>>>() {}.type
            val resultList: List<Map<String, Any>> = Gson().fromJson(resultadoJson, type)
            
            // Mapeia de volta para DetalhePokemon1 para usar o adapter existente
            // Note: Simplificando para o exemplo, em um caso real você pegaria os objetos originais da lista
            val pokemonList = resultList.map { item ->
                // Aqui você pode buscar o objeto original da sua lista local usando o ID
                // ou reconstruir o objeto. Para agilidade, vamos assumir que o filtro retorna o necessário.
                // Vou apenas emitir a lista filtrada se você tiver uma forma de converter.
                // Como sugestão, filtre a lista original baseada nos IDs retornados pelo Python.
                val id = (item["id"] as Double).toInt()
                // ... lógica de recuperação ...
            }
            
            // Para este exemplo, vamos simplificar a busca Python retornando apenas os IDs
            // e filtrando a lista original no Kotlin para garantir integridade dos dados.
            filterOriginalListByIds(resultadoJson)
            _loading.value = false
        }
    }

    private fun filterOriginalListByIds(jsonIds: String) {
        viewModelScope.launch {
            val idsType = object : TypeToken<List<Map<String, Any>>>() {}.type
            val listWithData: List<Map<String, Any>> = Gson().fromJson(jsonIds, idsType)
            val ids = listWithData.map { (it["id"] as Double).toInt() }
            
            val originalList = getDetailPokemon1UseCase()
            val filtered = originalList.filter { it.id in ids }
            // Mantém a ordem do Python (que está ordenada por stat)
            val sortedFiltered = ids.mapNotNull { id -> filtered.find { it.id == id } }
            _pokemonResultados.postValue(sortedFiltered)
        }
    }
}
