package com.hadeer.triviaapplication.ui.questions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hadeer.triviaapplication.R
import com.hadeer.triviaapplication.databinding.FragmentGameBinding


class GameFragment : Fragment() {
    private lateinit var binding : FragmentGameBinding
    private lateinit var viewModel : QuestionViewModel
    private lateinit var recyclerView : RecyclerView
    private lateinit var adaptor : AnswersAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[QuestionViewModel::class.java]
        handleNavigateState()
        handleRenderData()
        return binding.root
    }

    private fun handleNavigateState () {
        viewModel.gameFinishedEvent.observe(viewLifecycleOwner, Observer { isTrue ->
            if(isTrue){
                val argumants = Bundle().apply {
                    viewModel.currentResult.value?.let { value -> putInt("result", value) }
                }
                viewModel.onGameFinishComplete()
                findNavController().navigate(R.id.action_gameFragment_to_gameResultFragment, argumants)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linkRecyclerViewWithAdaptor()

    }

    private fun handleRenderData() {
        viewModel.currentQuestion.observe(viewLifecycleOwner){question ->
            binding.textQuestion.text = question.question
            adaptor = AnswersAdaptor(question.answers)
            recyclerView.adapter = adaptor
            binding.submiteBtn.setOnClickListener{
                val selectedAnswer = adaptor.getSelectedAnswer()
                if(selectedAnswer == null){
                    Toast.makeText(requireContext(),"Please select answer" , Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if(selectedAnswer ==question.correctAns){
                    viewModel.updateResult()
                }
                viewModel.goToNextQuestion()
            }
        }
    }

    private fun linkRecyclerViewWithAdaptor(){
        recyclerView = binding.answersContainerInclude.answersItemRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}