package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.gen_iii

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.PokemonDetailGen3
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.gen_iii.PokemonDetailGen3Repository
import javax.inject.Inject

class GetPokemonDetailGen3UseCase @Inject constructor(
    private val repository: PokemonDetailGen3Repository
) {
    suspend operator fun invoke(id: String): PokemonDetailGen3? {
        return repository.getPokemonDetailGen3(id)
    }
}
