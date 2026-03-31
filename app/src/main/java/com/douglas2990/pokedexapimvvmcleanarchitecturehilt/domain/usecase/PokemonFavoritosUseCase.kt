package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.PokemonFavorito
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.PokemonFavoritosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoritosUseCase @Inject constructor(
    private val repository: PokemonFavoritosRepository
) {
    operator fun invoke(): Flow<List<PokemonFavorito>> = repository.getFavoritos()
}

class SalvarFavoritoUseCase @Inject constructor(
    private val repository: PokemonFavoritosRepository
) {
    suspend operator fun invoke(pokemon: PokemonFavorito) = repository.salvarFavorito(pokemon)
}

class RemoverFavoritoUseCase @Inject constructor(
    private val repository: PokemonFavoritosRepository
) {
    suspend operator fun invoke(pokemon: PokemonFavorito) = repository.removerFavorito(pokemon)
}

class IsFavoritoUseCase @Inject constructor(
    private val repository: PokemonFavoritosRepository
) {
    suspend operator fun invoke(id: Int) = repository.isFavorito(id)
}
