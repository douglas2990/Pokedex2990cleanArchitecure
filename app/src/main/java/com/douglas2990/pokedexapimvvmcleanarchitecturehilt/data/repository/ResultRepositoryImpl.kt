package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.repository

import android.util.Log
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.dto.toResult
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.data.remote.DummyAPI
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.Resultado
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.respository.ResultRepository
import javax.inject.Inject

class ResultRepositoryImpl @Inject constructor(
    private val dummyAPI: DummyAPI
): ResultRepository {
    override suspend fun recuperarResult(): List<Resultado> {
        try {
            val resposta = dummyAPI.getPokemon(2000,0)
            if( resposta.isSuccessful && resposta.body() != null){
                val resultAPIDTO = resposta.body()
                val listResult = resultAPIDTO?.results
                if( listResult != null ){
                    /*
                    val usuarios = listaUsuarios.map {usuarioDTO ->
                        usuarioDTO.toUsuario()
                     */
                    return listResult.map {it.toResult() }
                }
            }else{
                Log.i("lista_usuarios", "${resposta.message()}")
            }
        }catch (erroRecuperarListaResult: Exception){
            erroRecuperarListaResult.printStackTrace()
        }

        return emptyList()
    }
}