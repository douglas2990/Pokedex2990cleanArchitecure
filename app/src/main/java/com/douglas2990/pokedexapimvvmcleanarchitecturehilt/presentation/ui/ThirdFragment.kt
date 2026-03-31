package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.ThirdFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdListBinding? = null
    private val binding get() = _binding!!

    // Usando agora a ViewModel dedicada apenas para esta Fragment
    private val viewModel by viewModels<ThirdFragmentViewModel>()
    private var adapter: PokemonAdapterTypesDetail? = null

    private val pokemonListener = object : PokemonInterface {
        override fun onClick(pokemonId: String) {
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
        setupObservers()
        setupSearchView()
    }

    private fun setupObservers() {
        // Observa a lista de Pokémons filtrada ou completa vinda da ViewModel
        viewModel.listaPokemon.observe(viewLifecycleOwner) { resultPokemon ->
            if (adapter == null) {
                adapter = PokemonAdapterTypesDetail(resultPokemon, requireContext(), pokemonListener)
                binding.recyclerThird.adapter = adapter
            } else {
                adapter?.updateList(resultPokemon)
            }
        }

        // Observa o estado de carregamento
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.thirdProgress.isVisible = isLoading
        }
    }

    private fun setupSearchView() {
        binding.searchPokemonThird.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                // Apenas repassa o texto para a ViewModel tratar
                viewModel.filterList(newText)
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
