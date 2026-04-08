package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentGeneration3OptionsBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.Generation3ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Generation3OptionsFragment : Fragment() {

    private var _binding: FragmentGeneration3OptionsBinding? = null
    private val binding get() = _binding!!

    // Adicionando a ViewModel para buscar os nomes e contagens
    private val viewModel by viewModels<Generation3ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneration3OptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupClickListeners()
        
        // Dispara a busca dos dados
        viewModel.fetchGen3Data()
    }

    private fun setupObservers() {
        viewModel.gen3Data.observe(viewLifecycleOwner) { gen3 ->
            gen3?.let {
                // Aqui preenchemos o texto real nos botões
                binding.btnRegion.text = "Região: ${it.mainRegion.uppercase()}"
                binding.btnPokemonCount.text = "Total Pokémons: ${it.pokemonCount}"
                binding.btnMoveCount.text = "Total Movimentos: ${it.moveCount}"
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnPokemonCount.setOnClickListener {
            val bundle = bundleOf("listType" to "pokemon")
            findNavController().navigate(R.id.action_Generation3OptionsFragment_to_Generation3Fragment, bundle)
        }

        binding.btnMoveCount.setOnClickListener {
            findNavController().navigate(R.id.action_Generation3OptionsFragment_to_Generation3MovesFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
