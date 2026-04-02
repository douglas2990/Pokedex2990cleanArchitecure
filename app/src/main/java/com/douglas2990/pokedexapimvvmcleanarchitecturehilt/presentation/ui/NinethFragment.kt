package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

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
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentNinethBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.PokemonAdapterPython
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.PokemonInterface
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.NinethViewModelPython
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NinethFragment : Fragment() {

    private var _binding: FragmentNinethBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<NinethViewModelPython>()
    private var adapter: PokemonAdapterPython? = null

    private val pokemonListener = object : PokemonInterface {
        override fun onClick(pokemonId: String) {
            findNavController().navigate(
                R.id.action_NinethFragment_to_SecondFragment,
                bundleOf("id" to pokemonId)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNinethBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupObservers()
        setupSearchView()
        
        binding.btnShowAll.setOnClickListener {
            viewModel.showAll()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerPython.layoutManager = LinearLayoutManager(context)
    }

    private fun setupObservers() {
        viewModel.pokemonResultados.observe(viewLifecycleOwner) { resultList ->
            if (adapter == null) {
                adapter = PokemonAdapterPython(resultList, requireContext(), pokemonListener)
                binding.recyclerPython.adapter = adapter
            } else {
                adapter?.updateList(resultList)
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.pythonProgress.isVisible = isLoading
        }
    }

    private fun setupSearchView() {
        binding.searchPython.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrBlank()) {
                    viewModel.searchWithPython(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
