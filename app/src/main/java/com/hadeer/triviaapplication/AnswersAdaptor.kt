package com.hadeer.triviaapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hadeer.triviaapplication.databinding.AnswerRadioItemBinding

class AnswersAdaptor(val data : List<String>):
    RecyclerView.Adapter<AnswersAdaptor.AnswersViewHolder>() {

    class AnswersViewHolder(private val binding:AnswerRadioItemBinding): RecyclerView.ViewHolder(binding.root){
        val answerItem = binding.multipleChoiceItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswersViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = AnswerRadioItemBinding.inflate(inflater)
        return AnswersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: AnswersViewHolder, position: Int) {
        val renderItem = data[position]

        holder.answerItem.text = renderItem
    }
}