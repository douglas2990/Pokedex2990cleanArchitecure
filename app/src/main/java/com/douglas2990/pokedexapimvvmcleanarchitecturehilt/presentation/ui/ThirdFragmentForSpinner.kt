package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentThirdBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.PokemonSpinnerAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui.detail.DetailPokemonViewModel
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.PokemonListDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragmentForSpinner : Fragment() {

    private var _binding: FragmentThirdBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding !!

    private val pokemonListDetailViewModel by viewModels<PokemonListDetailViewModel>()

    private val detailPokemonViewModel by viewModels<DetailPokemonViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root

    }
    @SuppressLint("FragmentLiveDataObserve", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPokemonListDetailSpinnerViewModel()

    }

    fun initPokemonListDetailSpinnerViewModel() {
        pokemonListDetailViewModel.listaDetailPokemon.observe(viewLifecycleOwner) { resultPokemon ->

            binding.spinnerPokemon.adapter =
                PokemonSpinnerAdapter(requireContext().applicationContext, resultPokemon)
            binding.spinnerPokemon?.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        p2: Int,
                        p3: Long
                    ) {
                        val nomePokemon = p1?.findViewById<TextView>(R.id.txtNomePokemon)
                        val numeroPokemon = p1?.findViewById<TextView>(R.id.txtNumeroPokemon)
                        var numeroPokemon3 =
                            p1?.findViewById<TextView>(R.id.txtNumeroPokemon)?.text.toString()
                        var numeroPokemon4 = (p2 + 1).toString()
                        var numeroPokemon5 = p2 + 1


                        binding.editTextGambiarra.setText(numeroPokemon4)

                        binding.buttonFirst.text = numeroPokemon4

                        detailPokemonViewModel.recuperarPokemon(numeroPokemon4)

                        detailPokemonViewModel.detalhePokemon.observe(viewLifecycleOwner) { resultPokemon ->

                            binding.detailNamePokemon.text = "#" + resultPokemon?.id.toString()
                                .padStart(3, '0') + " " + resultPokemon?.nome.toString()
                            binding.detailPokemon.load(resultPokemon?.esprites?.other?.home?.front_default)

                        }


                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                        TODO("Not yet implemented")
                    }

                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}