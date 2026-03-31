package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.di

import android.content.Context
import androidx.room.Room
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.dao.PokemonDao
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.local.database.AppDatabase
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.remote.DummyAPI
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.repository.*
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.*
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.usecase.*
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.util.Constantes.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModulo {

    @Provides
    @Singleton
    fun proverRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun proverDummyApi(retrofit: Retrofit): DummyAPI {
        return retrofit.create(DummyAPI::class.java)
    }

    // Room Database
    @Provides
    @Singleton
    fun proverDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "pokedex_db"
        ).build()
    }

    @Provides
    fun proverPokemonDao(database: AppDatabase): PokemonDao {
        return database.pokemonDao()
    }

    // Repositories
    @Provides
    @Singleton
    fun proverResultRespository(dummyAPI: DummyAPI): ResultRepository {
        return ResultRepositoryImpl(dummyAPI)
    }

    @Provides
    @Singleton
    fun proverDetailRespository(dummyAPI: DummyAPI): DetalhePokemonRepository {
        return DetailPokemonRepositoryImpl(dummyAPI)
    }

    @Provides
    @Singleton
    fun proverDetail1Respository(dummyAPI: DummyAPI): DetalhePokemon1Repository {
        return DetailPokemon1RepositoryImpl(dummyAPI)
    }

    @Provides
    @Singleton
    fun proverPokemonSpecieRepository(dummyAPI: DummyAPI): PokemonSpecieRepository {
        return PokemonSpecieRepositoryImpl(dummyAPI)
    }

    @Provides
    @Singleton
    fun proverPokemonFavoritosRepository(pokemonDao: PokemonDao): PokemonFavoritosRepository {
        return PokemonFavoritosRepositoryImpl(pokemonDao)
    }

    // UseCases
    @Provides
    fun proverResultUseCase(resultRepository: ResultRepository): GetResultUseCase {
        return GetResultUseCase(resultRepository)
    }

    @Provides
    fun proverDetailUseCase(detailRepository: DetalhePokemonRepository): GetDetailPokemonUseCase {
        return GetDetailPokemonUseCase(detailRepository)
    }

    @Provides
    fun proverPokemonSpecieUseCase(pokemonSpecieRepository: PokemonSpecieRepository): GetPokemonSpecieUseCase {
        return GetPokemonSpecieUseCase(pokemonSpecieRepository)
    }
}
