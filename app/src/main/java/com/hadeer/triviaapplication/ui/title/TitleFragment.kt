package com.hadeer.triviaapplication.ui.title

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.hadeer.triviaapplication.R
import com.hadeer.triviaapplication.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    private lateinit var binding : FragmentTitleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTitleBinding.inflate(inflater, container, false)
        handleBtnNavigation()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun handleBtnNavigation() {
        binding.actionStartBtn.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }
    }
}