package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentEightBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.PokemonMovesAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui.detail.DetailPokemonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EightFragment : Fragment() {

    private var _binding: FragmentEightBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel by viewModels<DetailPokemonViewModel>()

    private var isShiny = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEightBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonId = arguments?.getString("id") ?: ""

        binding.recyclerMoves.layoutManager = LinearLayoutManager(requireContext())

        detailViewModel.recuperarPokemon(pokemonId)

        observeViewModel()
    }

    private fun observeViewModel() {
        detailViewModel.detalhePokemon.observe(viewLifecycleOwner) { pokemon ->
            pokemon?.let {
                binding.txtNomeEight.text = it.nome.uppercase()
                
                val normalUrl = it.esprites.other?.home?.front_default
                val shinyUrl = it.esprites.other?.home?.front_shiny
                
                binding.imgEight.load(normalUrl)

                binding.imgEight.setOnClickListener {
                    isShiny = !isShiny
                    if (isShiny && shinyUrl != null) {
                        binding.imgEight.load(shinyUrl)
                    } else {
                        binding.imgEight.load(normalUrl)
                    }
                }

                binding.recyclerMoves.adapter = PokemonMovesAdapter(it.golpes)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}