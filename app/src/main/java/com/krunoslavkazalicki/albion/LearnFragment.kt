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
        units.add("Nouns")
        units.add("Verbs")
        units.add("Adjectives")

        view.findViewById<RecyclerView>(R.id.units_rv).apply {
            layoutManager = LinearLayoutManager(this@LearnFragment.context)
            adapter = UnitRecyclerAdapter(units)
        }

        return view
    }
}