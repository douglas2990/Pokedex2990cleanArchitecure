package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentThirdListBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.PokemonAdapterTypesDetail
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.PokemonInterface
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.PokemonListDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdListBinding? = null
    private val binding get() = _binding!!

    private val pokemonListDetailViewModel by viewModels<PokemonListDetailViewModel>()

    private val pokemonListener = object : PokemonInterface {
        override fun onClick(pokemonId: String) {
            // Navegando para a SixthFragment que criamos anteriormente
            findNavController().navigate(
                R.id.action_ThirdFragment_to_SixthFragment,
                bundleOf("id" to pokemonId)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerThird.layoutManager = LinearLayoutManager(context)
        initPokemonListDetailViewModel()
    }

    private fun initPokemonListDetailViewModel() {
        binding.thirdProgress.isVisible = true
        pokemonListDetailViewModel.listaDetailPokemon.observe(viewLifecycleOwner) { resultPokemon ->
            binding.thirdProgress.isVisible = false
            // Usando o adapter que já existe e mostra Imagem, Nome, Número e Tipos
            binding.recyclerThird.adapter = PokemonAdapterTypesDetail(
                resultPokemon,
                requireContext(),
                pokemonListener
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
