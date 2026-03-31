package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentTenthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TenthFragment : Fragment() {

    private var _binding: FragmentTenthBinding? = null
    private val binding get() = _binding!!
    private var pokemonId: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTenthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonId = arguments?.getString("id") ?: ""

        setupBottomNavigation()

        // Carrega a primeira fragment por padrão (SecondFragment - Info)
        if (savedInstanceState == null) {
            replaceFragment(SecondFragment())
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_second -> {
                    replaceFragment(SecondFragment())
                    true
                }
                R.id.nav_fourth -> {
                    replaceFragment(FourthFragment())
                    true
                }
                R.id.nav_sixth -> {
                    replaceFragment(SixthFragment())
                    true
                }
                R.id.nav_eight -> {
                    replaceFragment(EightFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val bundle = Bundle().apply {
            putString("id", pokemonId)
        }
        fragment.arguments = bundle
        
        childFragmentManager.beginTransaction()
            .replace(R.id.containerDetailed, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
