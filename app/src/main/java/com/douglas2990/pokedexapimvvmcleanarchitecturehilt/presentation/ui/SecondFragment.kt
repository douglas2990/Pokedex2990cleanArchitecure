package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentSecondBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui.detail.DetailPokemonViewModel
import coil.load
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.EggGroupAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.ListBaseStatsAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.ListTypeAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.PokemonSpeciesViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val detailPokemonViewModel by viewModels<DetailPokemonViewModel>()
    private val pokemonSpecieViewModel by viewModels<PokemonSpeciesViewModel>()

    var gridLayoutManager: GridLayoutManager? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding !!

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

        presentLoading(false)
        //binding.progressBarHp.progress = 200
        presentLoading(true)
        viewModelDetail()
        viewModelSpecies()
        //viewModelSpeciesEggGroup()








    }

    fun viewModelDetail(){
       // var gridLayoutManager: GridLayoutManager? = null

        detailPokemonViewModel.detalhePokemon.observe(viewLifecycleOwner){ resultPokemon->
            presentLoading(false)
            binding.detailNamePokemon.text = "#" + resultPokemon?.id.toString().padStart(3,'0') + " " + resultPokemon?.nome.toString()
            binding.detailPokemon.load(resultPokemon?.esprites?.other?.home?.front_default)
            gridLayoutManager = GridLayoutManager(
                context,
                resultPokemon!!.tipos.size
            )
            binding.recyclerView.layoutManager = gridLayoutManager
            binding.recyclerView.adapter = ListTypeAdapter(resultPokemon.tipos)

            binding.detailNamePokemon.setOnClickListener {
                //findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment)
                findNavController().navigate(R.id.action_SecondFragment_to_FourthFragment)
            }
            binding.recyclerViewBaseStatus.adapter = ListBaseStatsAdapter(resultPokemon.status)
        }

    }

    private fun viewModelSpecies(){
        pokemonSpecieViewModel.detalhePokemon.observe(viewLifecycleOwner){result->
            presentLoading(false)
            gridLayoutManager = GridLayoutManager(
                requireActivity().applicationContext,
                        //context,
                result?.grupoOvos?.size!!
            )
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

        }else {
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
        detailPokemonViewModel.recuperarPokemon(arguments?.getString("id","") ?: "")
        pokemonSpecieViewModel.recuperarPokemon(arguments?.getString("id","") ?: "")


    }


}