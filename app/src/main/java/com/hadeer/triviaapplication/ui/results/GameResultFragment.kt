package com.hadeer.triviaapplication.ui.results

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hadeer.triviaapplication.R
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
        //We’ll start by adding setHasOptionsMenu(true) to onCreateView() in our GameWonFragment.
        setHasOptionsMenu(true)
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
        setHasOptionsMenu(true)
    }
    private fun handleBtnNavigation() {
        binding.actionResultBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun shareIntentText() : Intent{
        val res = arguments?.getInt("result")
//        val sharedIntent = ShareCompat.IntentBuilder.from(activity)
//            .setType("text/plain")
//            .setText(getString(R.string.your_score_is, res , 4))
//            .intent
        val sharedIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, getString(R.string.your_score_is, res , 4))
            type = "text/plain"
        }
        return sharedIntent
//        startActivity(sharedIntent)
    }

    private fun successStartActivity(){
        startActivity(shareIntentText())
    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        //to show the action of the navigation menu item
        inflater.inflate(R.menu.app_menu, menu)
        //check if the activity resolves to avoid app crash when remove try prom SharedIntent
        if (null == shareIntentText().resolveActivity(requireActivity().packageManager)) {
            // hide the menu item if it doesn't resolve
            menu.findItem(R.id.share)?.setVisible(false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.share){
            successStartActivity()
        }
        return super.onOptionsItemSelected(item)
    }
}