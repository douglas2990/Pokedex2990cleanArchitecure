package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.PokemonFavorito
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetFavoritosUseCase
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.IsFavoritoUseCase
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.RemoverFavoritoUseCase
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.SalvarFavoritoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritosViewModel @Inject constructor(
    private val getFavoritosUseCase: GetFavoritosUseCase,
    private val salvarFavoritoUseCase: SalvarFavoritoUseCase,
    private val removerFavoritoUseCase: RemoverFavoritoUseCase,
    private val isFavoritoUseCase: IsFavoritoUseCase
) : ViewModel() {

    val listaFavoritos: LiveData<List<PokemonFavorito>> = getFavoritosUseCase().asLiveData()

    private val _isFavorito = MutableLiveData<Boolean>()
    val isFavorito: LiveData<Boolean> get() = _isFavorito

    fun checkIsFavorito(id: Int) {
        viewModelScope.launch {
            _isFavorito.value = isFavoritoUseCase(id)
        }
    }

    fun toggleFavorito(pokemon: PokemonFavorito) {
        viewModelScope.launch {
            if (_isFavorito.value == true) {
                removerFavoritoUseCase(pokemon)
                _isFavorito.value = false
            } else {
                salvarFavoritoUseCase(pokemon)
                _isFavorito.value = true
            }
        }
    }
}
