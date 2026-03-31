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
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentSeventhBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.FavoritosAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.FavoritosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeventhFragment : Fragment() {

    private var _binding: FragmentSeventhBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<FavoritosViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeventhBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerFavoritos.layoutManager = GridLayoutManager(context, 2)
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.listaFavoritos.observe(viewLifecycleOwner) { favoritos ->
            binding.txtEmpty.isVisible = favoritos.isEmpty()
            binding.recyclerFavoritos.adapter = FavoritosAdapter(favoritos) { pokemonId ->
                // Navega para os detalhes ao clicar em um favorito
                findNavController().navigate(
                    R.id.action_SeventhFragment_to_TenthFragment,
                    bundleOf("id" to pokemonId)
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
