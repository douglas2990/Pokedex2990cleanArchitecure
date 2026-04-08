package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.gen_iii

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.Generation3
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.gen_iii.Generation3Repository
import javax.inject.Inject

class GetGeneration3UseCase @Inject constructor(
    private val repository: Generation3Repository
) {
    suspend operator fun invoke(): Generation3? {
        return repository.getGeneration3Data()
    }
}
