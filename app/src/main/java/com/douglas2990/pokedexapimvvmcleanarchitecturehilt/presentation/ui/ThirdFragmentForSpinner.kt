package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentThirdForSpinnerBinding
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.PokemonSpinnerAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.SixthBaseStatsAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.adapter.ListTypeAdapter
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui.detail.DetailPokemonViewModel
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.viewmodel.PokemonListDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdFragmentForSpinner : Fragment() {

    private var _binding: FragmentThirdForSpinnerBinding? = null
    private val binding get() = _binding!!

    // ALTERAÇÃO: Usando activityViewModels para pegar os dados que a Splash já carregou
    private val pokemonListDetailViewModel by activityViewModels<PokemonListDetailViewModel>()
    private val detailPokemonViewModel by viewModels<DetailPokemonViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdForSpinnerBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("FragmentLiveDataObserve", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        initObservers()
        initPokemonListDetailSpinnerViewModel()
    }

    private fun setupRecyclerViews() {
        binding.recyclerViewDetail.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewTypes.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initObservers() {
        detailPokemonViewModel.detalhePokemon.observe(viewLifecycleOwner) { pokemon ->
            pokemon?.let {
                binding.recyclerViewDetail.adapter = SixthBaseStatsAdapter(it.status)
            }
        }
    }

    private fun initPokemonListDetailSpinnerViewModel() {
        // Como a Splash já garantiu que a lista está carregada, isso será disparado imediatamente
        pokemonListDetailViewModel.listaDetailPokemon.observe(viewLifecycleOwner) { resultPokemon ->
            if (resultPokemon != null && resultPokemon.isNotEmpty()) {
                val adapter = PokemonSpinnerAdapter(requireContext(), resultPokemon)
                binding.spinnerPokemon.adapter = adapter
                
                binding.spinnerPokemon.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val pokemonSelecionado = resultPokemon[position]
                        
                        // Atualiza interface com dados já disponíveis na memória
                        binding.detailNamePokemon.text = "#${pokemonSelecionado.id.toString().padStart(3, '0')} ${pokemonSelecionado.nome.uppercase()}"
                        binding.detailPokemon.load(pokemonSelecionado.esprites.other.home.front_default)
                        binding.recyclerViewTypes.adapter = ListTypeAdapter(pokemonSelecionado.tipos)
                        
                        // Busca apenas os Stats em background
                        detailPokemonViewModel.recuperarPokemon(pokemonSelecionado.id.toString())
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
