package com.hadeer.triviaapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
        decideViewResult()
        return binding.root
    }

    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    private fun decideViewResult() {
        val res = arguments?.getInt("result")
            println("correct answers is $res")
        if (res != null) {
            if(res >= 4){
                binding.wonImg.setImageResource(R.drawable.you_win)
                binding.actionResultBtn.text = getString(R.string.next_match)
            } else{
                binding.wonImg.setImageResource(R.drawable.try_again)
                binding.actionResultBtn.text = getString(R.string.try_again)
            }
        }
        binding.congratsText.text = " ${getString(R.string.your_score_is)} $res / 4"

    }

    private fun handleBtnNavigation() {
        binding.actionResultBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}