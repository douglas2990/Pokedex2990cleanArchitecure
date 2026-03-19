package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentFourthBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.EggGroupAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.ListBaseStatsAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.ListTypeAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui.detail.DetailPokemonViewModel
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.PokemonSpeciesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FourthFragment : Fragment() {

    private var _binding: FragmentFourthBinding? = null
    private val binding get() = _binding!!

    private val detailPokemonViewModel by viewModels<DetailPokemonViewModel>()
    private val pokemonSpecieViewModel by viewModels<PokemonSpeciesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        observeViewModels()
    }

    private fun setupRecyclerViews() {
        binding.recyclerViewEggGroup.layoutManager = LinearLayoutManager(context)
        //binding.recyclerViewBaseStatus.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewTypes.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModels() {
        presentLoading(true)

        detailPokemonViewModel.detalhePokemon.observe(viewLifecycleOwner) { resultPokemon ->
            resultPokemon?.let { pokemon ->
                presentLoading(false)
                
                binding.detailNamePokemon.text = "#${pokemon.id.toString().padStart(3, '0')} ${pokemon.nome.uppercase()}"
                binding.detailPokemon.load(pokemon.esprites.other.home.front_default)
                
                // Types
                binding.recyclerViewTypes.adapter = ListTypeAdapter(pokemon.tipos)

                // Stats List (Optional, since we have individual progress bars)
               // binding.recyclerViewBaseStatus.adapter = ListBaseStatsAdapter(pokemon.status)

                // Update individual ProgressBars
                pokemon.status.forEach { stat ->
                    when (stat.stat.name.lowercase()) {
                        "hp" -> {
                            binding.progressBarHp.progress = stat.base_stat
                            binding.textHpValue.text = stat.base_stat.toString()
                        }
                        "attack" -> {
                            binding.progressBarAttack.progress = stat.base_stat
                            binding.textAttackValue.text = stat.base_stat.toString()
                        }
                        "defense" -> {
                            binding.progressBarDefense.progress = stat.base_stat
                            binding.textDefenseValue.text = stat.base_stat.toString()
                        }
                        "special-attack" -> {
                            binding.progressBarSpAtk.progress = stat.base_stat
                            binding.textSpAtkValue.text = stat.base_stat.toString()
                        }
                        "special-defense" -> {
                            binding.progressBarSpDef.progress = stat.base_stat
                            binding.textSpDefValue.text = stat.base_stat.toString()
                        }
                        "speed" -> {
                            binding.progressBarSpeed.progress = stat.base_stat
                            binding.textSpeedValue.text = stat.base_stat.toString()
                        }
                    }
                }
            }
        }

        pokemonSpecieViewModel.detalhePokemon.observe(viewLifecycleOwner) { result ->
            result?.let { species ->
                binding.recyclerViewEggGroup.adapter = EggGroupAdapter(species.grupoOvos)
            }
        }
    }

    private fun presentLoading(show: Boolean) {
        if (show) {
            binding.detailsShimmerPokemon.showShimmer(true)
            binding.detaisShimmerNamePokemon.showShimmer(true)
            binding.detaisShimmerType.showShimmer(true)
            binding.detaisShimmerEggGroup.showShimmer(true)

        } else {
            binding.detailsShimmerPokemon.hideShimmer()
            binding.detaisShimmerNamePokemon.hideShimmer()
            binding.detaisShimmerType.hideShimmer()
            binding.detaisShimmerEggGroup.hideShimmer()

        }
    }

    override fun onStart() {
        super.onStart()
        val pokemonId = arguments?.getString("id", "") ?: ""
        detailPokemonViewModel.recuperarPokemon(pokemonId)
        pokemonSpecieViewModel.recuperarPokemon(pokemonId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
