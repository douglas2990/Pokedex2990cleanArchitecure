package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.genIII

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.Generation3
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.gen_iii.GetGeneration3UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Generation3ViewModel @Inject constructor(
    private val getGeneration3UseCase: GetGeneration3UseCase
) : ViewModel() {

    private val _gen3Data = MutableLiveData<Generation3?>()
    val gen3Data: LiveData<Generation3?> get() = _gen3Data

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    fun fetchGen3Data() {
        _loading.value = true
        viewModelScope.launch {
            val result = getGeneration3UseCase()
            _gen3Data.value = result
            _loading.value = false
        }
    }
}
