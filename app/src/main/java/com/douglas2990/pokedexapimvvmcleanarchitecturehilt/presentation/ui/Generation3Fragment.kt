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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentGeneration3Binding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.Generation3ListMovesAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.PokemonSpeciesGenIIIAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.Generation3ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Generation3Fragment : Fragment() {

    private var _binding: FragmentGeneration3Binding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<Generation3ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneration3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupClickListeners()
        
        viewModel.fetchGen3Data()
    }

    private fun setupClickListeners() {

    }

    private fun switchToPokemonGrid() {
        viewModel.gen3Data.value?.let { gen3 ->
            binding.recyclerGen3.layoutManager = GridLayoutManager(context, 3)
            binding.recyclerGen3.adapter = PokemonSpeciesGenIIIAdapter(gen3.pokemonSpecies) { pokemonId ->
                findNavController().navigate(
                    R.id.action_Generation3Fragment_to_Generation3PokemonDetailFragment,
                    bundleOf("id" to pokemonId)
                )
            }
        }
    }

    private fun switchToMovesList() {
        viewModel.gen3Data.value?.let { gen3 ->
            binding.recyclerGen3.layoutManager = LinearLayoutManager(context)
            binding.recyclerGen3.adapter = Generation3ListMovesAdapter(gen3.moves)
        }
    }

    private fun setupObservers() {
        viewModel.gen3Data.observe(viewLifecycleOwner) { gen3 ->
            gen3?.let {

                
                // Decide qual lista mostrar baseado no argumento vindo das Opções
                val listType = arguments?.getString("listType") ?: "pokemon"
                if (listType == "moves") {
                    switchToMovesList()
                } else {
                    switchToPokemonGrid()
                }
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressGen3.isVisible = isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
