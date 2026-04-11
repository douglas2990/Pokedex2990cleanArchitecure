package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui.genII

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
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.genIII.StatPokemonGen3Adapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.genIII.TypePokemonGen3Adapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.genIII.Generation3ListMovesAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.EggGroupAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.genIII.Gen3PokemonDetailViewModel
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.PokemonSpeciesViewModel
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.MoveGenIII
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.gen_iii.MoveGenIIIDetail
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
        // Stats com LinearLayout Vertical
        binding.recyclerViewBaseStatus.layoutManager = LinearLayoutManager(context)

        // Egg Groups Horizontal
        binding.recyclerViewEggGroup.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        // Moves Vertical
        binding.recyclerViewMoves.layoutManager = LinearLayoutManager(context)
        // Otimização de performance para listas longas de golpes
        binding.recyclerViewMoves.setHasFixedSize(true)
    }

    private fun setupObservers() {
        viewModel.pokemonDetail.observe(viewLifecycleOwner) { detail ->
            detail?.let { pokemon ->
                presentLoading(false)

                // Nome e ID formatado (#001 NAME)
                binding.detailNamePokemon.text = "#${pokemon.id.toString().padStart(3, '0')} ${pokemon.nome.uppercase()}"

                // Prioridade para sprites de Generation III (GBA)
                val gbaImage = pokemon.sprites.versions?.generationIII?.rubySapphire?.frontDefault
                    ?: pokemon.sprites.versions?.generationIII?.emerald?.frontDefault
                    ?: pokemon.sprites.other?.officialArtwork?.frontDefault
                    ?: pokemon.sprites.frontDefault
                    ?: ""
                binding.detailPokemon.load(gbaImage)

                // Tipos do Pokémon (Grid dinâmico baseado na quantidade de tipos)
                binding.recyclerTypeGen3.layoutManager = GridLayoutManager(context, pokemon.tipos.size)
                binding.recyclerTypeGen3.adapter = TypePokemonGen3Adapter(pokemon.tipos)

                // Estatísticas Base
                binding.recyclerViewBaseStatus.adapter = StatPokemonGen3Adapter(pokemon.status)

                // --- CORREÇÃO AQUI ---
                // Não use o .map { ... } que cria placeholders.
                // Use a lista que já veio pronta e detalhada do seu Repository!
                binding.recyclerViewMoves.adapter = Generation3ListMovesAdapter(pokemon.golpes)
            }
        }

        // Egg Groups vindo do SpeciesViewModel (Fica em um Card separado)
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
            binding.detailsShimmerPokemon.startShimmer()
            binding.detaisShimmerNamePokemon.startShimmer()
            binding.detaisShimmerType.startShimmer()
            binding.detaisShimmerEggGroup.startShimmer()
            binding.detaisBaseStatus.startShimmer()

            // Opcional: Esconder o conteúdo enquanto carrega
            binding.detailPokemon.visibility = View.INVISIBLE
        } else {
            binding.detailsShimmerPokemon.stopShimmer()
            binding.detailsShimmerPokemon.hideShimmer()

            binding.detaisShimmerNamePokemon.stopShimmer()
            binding.detaisShimmerNamePokemon.hideShimmer()

            binding.detaisShimmerType.stopShimmer()
            binding.detaisShimmerType.hideShimmer()

            binding.detaisShimmerEggGroup.stopShimmer()
            binding.detaisShimmerEggGroup.hideShimmer()

            binding.detaisBaseStatus.stopShimmer()
            binding.detaisBaseStatus.hideShimmer()

            binding.detailPokemon.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


