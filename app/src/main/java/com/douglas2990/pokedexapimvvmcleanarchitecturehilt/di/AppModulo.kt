package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.di

import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.remote.DummyAPI
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.repository.DetailPokemon1RepositoryImpl
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.repository.DetailPokemonRepositoryImpl
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.repository.PokemonSpecieRepositoryImpl
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.repository.ResultRepositoryImpl
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.DetalhePokemon1Repository
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.DetalhePokemonRepository
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.PokemonSpecieRepository
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.ResultRepository
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetDetailPokemonUseCase
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetPokemonSpecieUseCase
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.GetResultUseCase
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.util.Constantes.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn( ViewModelComponent::class )
object AppModulo {

    @Provides
    fun proverRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
    }

    @Provides
    fun proverDummyApi(
        retrofit: Retrofit
    ) : DummyAPI {
        return retrofit.create( DummyAPI::class.java )
    }

    @Provides
    fun proverResultRespository(
        dummyAPI: DummyAPI
    ) : ResultRepository {
        return ResultRepositoryImpl( dummyAPI )
    }
    @Provides
    fun proverDetailRespository(
        dummyAPI: DummyAPI
    ) : DetalhePokemonRepository {
        return DetailPokemonRepositoryImpl( dummyAPI )
    }


    @Provides
    fun proverDetail1Respository(
        dummyAPI: DummyAPI,
        //context: Context
    ) : DetalhePokemon1Repository {
        return DetailPokemon1RepositoryImpl( dummyAPI )
    }
    @Provides
    fun proverPokemonSpecieRespository(
        dummyAPI: DummyAPI,
    ) : PokemonSpecieRepository {
        return PokemonSpecieRepositoryImpl( dummyAPI )
    }


    @Provides
    fun proverResultUseCase( resultRepository: ResultRepository
    ) : GetResultUseCase {
        return GetResultUseCase( resultRepository )
    }

    @Provides
    fun proverDetailUseCase( detailRepository: DetalhePokemonRepository
    ) : GetDetailPokemonUseCase {
        return GetDetailPokemonUseCase( detailRepository )
    }



    @Provides
    fun proverPokemonSpecieUseCase( pokemonSpecieRepository: PokemonSpecieRepository
    ) : GetPokemonSpecieUseCase {
        return GetPokemonSpecieUseCase( pokemonSpecieRepository )
    }












    /*
    @Provides
    fun proverDetail1UseCase( detail1Repository: DetalhePokemon1Repository
    ) : GetDetailPokemon1UseCase {
        return GetDetailPokemon1UseCase( detail1Repository )
    }

     */


}