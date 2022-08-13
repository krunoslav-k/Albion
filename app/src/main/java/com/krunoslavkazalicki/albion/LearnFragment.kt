package com.krunoslavkazalicki.albion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LearnFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_learn, container, false)
        val units = ArrayList<String>(10)
        units.add("Verbs")
        units.add("Nouns")
        units.add("Adjectives")
        val verbsLessons: List<String> = listOf("Present Simple", "Present Continous", "Past Simple", "Past Continious", "Present Perfect Simple", "Present Perfect Continous", "Past Perfect Simple", "Past Perfect Continoius")
        val nounsLessons: List<String> = listOf("Countable", "Uncountable")
        val adjectivesLesssons: List<String> = listOf("Superlative", "General")
        var lessonsList: List<List<String>> = listOf(verbsLessons, nounsLessons, adjectivesLesssons)

        view.findViewById<RecyclerView>(R.id.units_rv).apply {
            layoutManager = LinearLayoutManager(this@LearnFragment.context)
            adapter = UnitRecyclerAdapter(units, lessonsList)
        }

        return view
    }
}