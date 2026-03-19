package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentFirstBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.ListPokemonAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.ListPokemonDetailAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.PokemonAdapterTypesDetail
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.PokemonInterface
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.PokemonListDetailViewModel
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.PokemonListViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val pokemonListViewModel by viewModels<PokemonListViewModel>()
    private val pokemonListDetailViewModel by viewModels<PokemonListDetailViewModel>()

    private val firstListener = object : PokemonInterface {
        override fun onClick(pokemonId: String){

            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment, bundleOf("id" to pokemonId))
            //findNavController().navigate(R.id.action_FirstFragment_to_FourthFragment, bundleOf("id" to pokemonId))
            findNavController().navigate(
                //R.id.action_FirstFragment_to_SixthFragment,
                R.id.action_FirstFragment_to_ThirdFragment,
                bundleOf("id" to pokemonId)
            )
        }
    }

    private val binding get() = _binding !!

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initPokemonListViewModel(){
        pokemonListViewModel.listaPokemon.observe(viewLifecycleOwner){ resultPokemon->
            binding.recyclerFirst.adapter = ListPokemonAdapter(resultPokemon, firstListener)
        }
    }

    fun initPokemonListDetailViewModel(){
        pokemonListDetailViewModel.listaDetailPokemon.observe(viewLifecycleOwner){ resultPokemon->
            binding.recyclerFirst.adapter = ListPokemonDetailAdapter(resultPokemon, firstListener)
        }
    }

    fun initPokemonListDetailTypeViewModel(){
        pokemonListDetailViewModel.listaDetailPokemon.observe(viewLifecycleOwner){ resultPokemon->
            binding.recyclerFirst.adapter = PokemonAdapterTypesDetail(resultPokemon,requireContext(), firstListener)
        }
    }
}
