package com.hadeer.triviaapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.hadeer.triviaapplication.databinding.FragmentGameResultBinding


class GameResultFragment : Fragment() {
    private lateinit var binding : FragmentGameResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameResultBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        handleBtnNavigation()
        return binding.root
    }

    private fun handleBtnNavigation() {
        binding.actionResultBtn.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_gameResultFragment_to_gameFragment)
        }
    }
}