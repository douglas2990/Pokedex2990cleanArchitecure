package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentSecondBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui.detail.DetailPokemonViewModel
import coil.load
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.PokemonFavorito
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.EggGroupAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.ListBaseStatsAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.ListTypeAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.FavoritosViewModel
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.PokemonSpeciesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val detailPokemonViewModel by viewModels<DetailPokemonViewModel>()
    private val pokemonSpecieViewModel by viewModels<PokemonSpeciesViewModel>()
    private val favoritosViewModel by viewModels<FavoritosViewModel>()
    
    var gridLayoutManager: GridLayoutManager? = null
    private val binding get() = _binding !!

    private var isShiny = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewEggGroup.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewBaseStatus.layoutManager = LinearLayoutManager(context)
        presentLoading(true)
        viewModelDetail()
        viewModelSpecies()
        setupFavoritosObserver()
    }

    private fun setupFavoritosObserver() {
        val pokemonId = arguments?.getString("id")?.toIntOrNull() ?: 0
        favoritosViewModel.checkIsFavorito(pokemonId)

        favoritosViewModel.isFavorito.observe(viewLifecycleOwner) { isFavorito ->
            if (isFavorito) {
                binding.fabFavorito.setImageResource(android.R.drawable.btn_star_big_on)
                binding.fabFavorito.imageTintList = ColorStateList.valueOf(Color.parseColor("#FFD700")) // Amarelo Ouro
            } else {
                binding.fabFavorito.setImageResource(android.R.drawable.btn_star_big_off)
                binding.fabFavorito.imageTintList = ColorStateList.valueOf(Color.GRAY) // Estrela "apagada"
            }
        }
    }

    fun viewModelDetail(){
        detailPokemonViewModel.detalhePokemon.observe(viewLifecycleOwner){ resultPokemon->
            presentLoading(false)
            binding.detailNamePokemon.text = "#" + resultPokemon?.id.toString().padStart(3,'0') + " " + resultPokemon?.nome.toString()
            
            val normalUrl = resultPokemon?.esprites?.other?.home?.front_default ?: ""
            val shinyUrl = resultPokemon?.esprites?.other?.home?.front_shiny
            
            binding.detailPokemon.load(normalUrl)

            binding.detailPokemon.setOnClickListener {
                isShiny = !isShiny
                if (isShiny && shinyUrl != null) {
                    binding.detailPokemon.load(shinyUrl)
                } else {
                    binding.detailPokemon.load(normalUrl)
                }
            }

            // Lógica do botão de favoritar com Mensagem
            binding.fabFavorito.setOnClickListener {
                resultPokemon?.let { pokemon ->
                    val favorito = PokemonFavorito(
                        id = pokemon.id,
                        nome = pokemon.nome,
                        imageUrl = normalUrl,
                        tipos = pokemon.tipos.joinToString(",") { it.type.name }
                    )
                    
                    val currentIsFavorito = favoritosViewModel.isFavorito.value ?: false
                    favoritosViewModel.toggleFavorito(favorito)
                    
                    if (!currentIsFavorito) {
                        Toast.makeText(context, "${pokemon.nome.uppercase()} adicionado aos favoritos!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "${pokemon.nome.uppercase()} removido dos favoritos!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            gridLayoutManager = GridLayoutManager(context, resultPokemon!!.tipos.size)
            binding.recyclerView.layoutManager = gridLayoutManager
            binding.recyclerView.adapter = ListTypeAdapter(resultPokemon.tipos)

            binding.detailNamePokemon.setOnClickListener {
                findNavController().navigate(R.id.action_SecondFragment_to_FourthFragment)
            }
            binding.recyclerViewBaseStatus.adapter = ListBaseStatsAdapter(resultPokemon.status)
        }
    }

    private fun viewModelSpecies(){
        pokemonSpecieViewModel.detalhePokemon.observe(viewLifecycleOwner){result->
            presentLoading(false)
            gridLayoutManager = GridLayoutManager(requireActivity().applicationContext, result?.grupoOvos?.size!!)
            binding.textView3.text = "EGG GROUP"
            binding.recyclerViewEggGroup.layoutManager = gridLayoutManager
            binding.recyclerViewEggGroup.adapter = EggGroupAdapter(result.grupoOvos)
        }
    }

    private fun presentLoading(show: Boolean){
        if (show){
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

    override fun onStart() {
        super.onStart()
        val id = arguments?.getString("id","") ?: ""
        detailPokemonViewModel.recuperarPokemon(id)
        pokemonSpecieViewModel.recuperarPokemon(id)
    }
}
