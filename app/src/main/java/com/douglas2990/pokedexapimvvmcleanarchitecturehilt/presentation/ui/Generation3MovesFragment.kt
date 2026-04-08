package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentGeneration3MovesBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.Generation3ListMovesAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.Generation3ViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Generation3MovesFragment : Fragment() {

    private var _binding: FragmentGeneration3MovesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<Generation3ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneration3MovesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerMovesGen3.layoutManager = LinearLayoutManager(context)
        setupObservers()
        
        viewModel.fetchGen3Data()
    }

    private fun setupObservers() {
        viewModel.gen3Data.observe(viewLifecycleOwner) { gen3 ->
            gen3?.let {
                binding.recyclerMovesGen3.adapter = Generation3ListMovesAdapter(it.moves)
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressMoves.isVisible = isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
