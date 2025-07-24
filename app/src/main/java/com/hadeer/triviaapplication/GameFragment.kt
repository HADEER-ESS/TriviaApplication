package com.hadeer.triviaapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.hadeer.triviaapplication.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private lateinit var binding : FragmentGameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linkRecyclerViewWithAdaptor()
    }

    private fun linkRecyclerViewWithAdaptor(){
        val recyclerView = binding.answersContainerInclude.answersItemRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.textQuestion.text = QuestionsData.Data[0].question
        recyclerView.adapter = AnswersAdaptor(QuestionsData.Data[0].answers)
    }
}