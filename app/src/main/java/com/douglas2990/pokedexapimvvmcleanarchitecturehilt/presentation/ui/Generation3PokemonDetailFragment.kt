package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentGeneration3PokemonDetailBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.StatPokemonGen3Adapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.TypePokemonGen3Adapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.Generation3ListMovesAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.EggGroupAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.Gen3PokemonDetailViewModel
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.PokemonSpeciesViewModel
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.MoveGenIII
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Generation3PokemonDetailFragment : Fragment() {

    private var _binding: FragmentGeneration3PokemonDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<Gen3PokemonDetailViewModel>()
    private val speciesViewModel by viewModels<PokemonSpeciesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneration3PokemonDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        setupObservers()
        
        val pokemonId = arguments?.getString("id") ?: ""
        viewModel.getPokemonDetail(pokemonId)
        speciesViewModel.recuperarPokemon(pokemonId)
        
        presentLoading(true)
    }

    private fun setupRecyclerViews() {
        binding.recyclerViewBaseStatus.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewEggGroup.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewMoves.layoutManager = LinearLayoutManager(context)
    }

    private fun setupObservers() {
        viewModel.pokemonDetail.observe(viewLifecycleOwner) { detail ->
            detail?.let { pokemon ->
                presentLoading(false)
                
                binding.detailNamePokemon.text = "#${pokemon.id.toString().padStart(3, '0')} ${pokemon.nome.uppercase()}"
                
                // Carregamento de imagem defensivo (GBA Ruby/Sapphire)
                val imageUrl = pokemon.sprites.other?.home?.frontDefault ?: pokemon.sprites.frontDefault ?: ""
                val gbaImage = pokemon.sprites.versions?.generationIII?.rubySapphire?.frontDefault
                    ?: pokemon.sprites.versions?.generationIII?.emerald?.frontDefault
                    ?: pokemon.sprites.other?.officialArtwork?.frontDefault
                    ?: pokemon.sprites.frontDefault
                    ?: ""
                binding.detailPokemon.load(gbaImage)

                // Tipos Históricos
                binding.recyclerTypeGen3.layoutManager = GridLayoutManager(context, pokemon.tipos.size)
                binding.recyclerTypeGen3.adapter = TypePokemonGen3Adapter(pokemon.tipos)

                // Estatísticas
                binding.recyclerViewBaseStatus.adapter = StatPokemonGen3Adapter(pokemon.status)

                // Restaurando o Generation3ListMovesAdapter para mostrar os golpes
                val movesList = pokemon.golpes.map { MoveGenIII(name = it, url = "") }
                binding.recyclerViewMoves.adapter = Generation3ListMovesAdapter(movesList)
            }
        }

        // Egg Groups via SpeciesViewModel
        speciesViewModel.detalhePokemon.observe(viewLifecycleOwner) { species ->
            species?.let {
                binding.recyclerViewEggGroup.adapter = EggGroupAdapter(it.grupoOvos)
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) presentLoading(true)
        }
    }

    private fun presentLoading(show: Boolean) {
        if (show) {
            binding.detailsShimmerPokemon.showShimmer(true)
            binding.detaisShimmerNamePokemon.showShimmer(true)
            binding.detaisShimmerType.showShimmer(true)
            binding.detaisShimmerEggGroup.showShimmer(true)
            binding.detaisBaseStatus.showShimmer(true)
        } else {
            binding.detailsShimmerPokemon.hideShimmer()
            binding.detaisShimmerNamePokemon.hideShimmer()
            binding.detaisShimmerType.hideShimmer()
            binding.detaisShimmerEggGroup.hideShimmer()
            binding.detaisBaseStatus.hideShimmer()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
