package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

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
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentFifthBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.ListPokemonAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.PokemonInterface
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FifthFragment : Fragment() {

    private var _binding: FragmentFifthBinding? = null
    private val binding get() = _binding!!

    private val pokemonListViewModel by viewModels<PokemonListViewModel>()

    private val pokemonListener = object : PokemonInterface {
        override fun onClick(pokemonId: String) {
            // Ao clicar, navega para a TenthFragment passando o ID
            findNavController().navigate(
                R.id.action_FifthFragment_to_TenthFragment,
                bundleOf("id" to pokemonId)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFifthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerFifth.layoutManager = LinearLayoutManager(context)
        initPokemonListViewModel()
    }

    private fun initPokemonListViewModel() {
        binding.fifthProgress.isVisible = true
        pokemonListViewModel.listaPokemon.observe(viewLifecycleOwner) { resultPokemon ->
            binding.fifthProgress.isVisible = false
            binding.recyclerFifth.adapter = ListPokemonAdapter(resultPokemon, pokemonListener)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
