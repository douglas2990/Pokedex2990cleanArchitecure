package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentFirstBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.domain.model.Resultado
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.ListPokemonAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.PokemonInterface
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.PokemonListDetailViewModel
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding !!

    private val pokemonListViewModel by viewModels<PokemonListViewModel>()
    private var adapter: ListPokemonAdapter? = null
    private var fullList: List<Resultado> = emptyList()

    private val firstListener = object : PokemonInterface {
        override fun onClick(pokemonId: String){
            findNavController().navigate(
                R.id.action_FirstFragment_to_SixthFragment,
                bundleOf("id" to pokemonId)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerFirst.layoutManager = LinearLayoutManager(context)
        binding.firstProgress.isVisible = view.isInvisible

        initPokemonListViewModel()
        setupSearchView()
    }

    private fun setupSearchView() {
        binding.searchPokemon.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }
        })
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredList = fullList.filter { pokemon ->
                val pokemonId = pokemon.urlDaApi.replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "")
                pokemon.nome.lowercase().contains(query.lowercase()) || pokemonId.contains(query)
            }
            adapter?.updateList(filteredList)
        }
    }

    fun initPokemonListViewModel(){
        pokemonListViewModel.listaPokemon.observe(viewLifecycleOwner){ resultPokemon->
            fullList = resultPokemon
            adapter = ListPokemonAdapter(resultPokemon, firstListener)
            binding.recyclerFirst.adapter = adapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
