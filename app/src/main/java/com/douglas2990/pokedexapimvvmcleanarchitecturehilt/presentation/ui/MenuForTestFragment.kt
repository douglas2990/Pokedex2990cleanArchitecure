package com.douglas2990.pokedexapimvvmcleanarchitecturehilt.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.R
import com.douglas2990.pokedexapimvvmcleanarchitecturehilt.databinding.FragmentMenuForTestBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuForTestFragment : Fragment() {

    private var _binding: FragmentMenuForTestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuForTestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtons()
    }

    private fun setupButtons() {
        binding.btnForFirstFragment.setOnClickListener {
            findNavController().navigate(R.id.action_menuForTestFragment_to_FirstFragment)
        }

        binding.btnForThirthFragment.setOnClickListener {
            findNavController().navigate(R.id.action_menuForTestFragment_to_ThirdFragment)
        }

        binding.btnForThirthforSpinner.setOnClickListener {
            findNavController().navigate(R.id.action_menuForTestFragment_to_SplashFragment)
        }

        binding.btnForFifthFragment.setOnClickListener {
            findNavController().navigate(R.id.action_menuForTestFragment_to_FifthFragment)
        }
        binding.btnForSeventhFragment.setOnClickListener {
            findNavController().navigate(R.id.action_menuForTestFragment_to_SeventhFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
