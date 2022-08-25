package com.krunoslavkazalicki.albion

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LearnFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_learn, container, false)
        val db = Firebase.firestore
        val fragmentManager: FragmentManager? = fragmentManager
        val units: List<String> = listOf("Verbs", "Nouns", "Pronouns", "Determiners", "Conditionals")

        val verbsLessonsA: List<String> = listOf("Present Simple", "Present Continuous", "Past Simple", "Past Continuous", "Present Perfect Simple", "Present Perfect Continuous", "Past Perfect Simple", "Past Perfect Continuous", "Future Simple (will)", "Future Simple (going to)", "Future Continuous", "Future Perfect Simple", "Future Perfect Continuous")
        val verbsLessonsB: List<String> = listOf("Present Simple", "Present Continuous", "Past Simple", "Past Continuous", "Present Perfect Simple", "Past Perfect Simple", "Past Perfect Continuous", "Future Simple (will)", "Future Simple (going to)", "Future Continuous")
        val verbsLessonsC: List<String> = listOf("Present Simple", "Present Continuous", "Past Simple", "Past Continuous", "Future Simple (will)", "Future Simple (going to)", "Future Continuous")

        val nounsLessonsA: List<String> = listOf("Plural", "Countable", "Uncountable", "Collective", "Possessive")
        val nounsLessonsB: List<String> = listOf("Plural", "Countable", "Uncountable", "Possessive")
        val nounsLessonsC = nounsLessonsB

        val pronounsLessonsA: List<String> = listOf("Personal", "Possessive", "Reflexive", "Relative", "Demonstrative", "Indefinite")
        val pronounsLessonsB: List<String> = pronounsLessonsA
        val pronounsLessonsC: List<String> = listOf("Personal", "Possessive", "Reflexive", "Demonstrative")

        val determinersLessonsA: List<String> = listOf("Articles", "Quantifiers", "Distributives")
        val determinersLessonsB: List<String> = listOf("Articles", "Quantifiers")
        val determinersLessonsC: List<String> = listOf("Articles")

        val conditionalsLessonsA: List<String> = listOf("Zero Conditional", "First Conditional", "Second Conditional", "Third Conditional", "Mixed Conditional", "Inverted Conditional")
        val conditionalsLessonsB: List<String> = listOf("Zero Conditional", "First Conditional", "Second Conditional", "Third Conditional")
        val conditionalsLessonsC: List<String> = listOf("Zero Conditional", "First Conditional")

        db.collection("Results")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener { result ->

                    var lessonsList: List<List<String>> = ArrayList<List<String>>()

                    for (document in result) {
                        var currentLevelScore: Int = document.data.get("result").toString().toInt()

                        if(currentLevelScore >= 21){
                            lessonsList = listOf(verbsLessonsA, nounsLessonsA, pronounsLessonsA, determinersLessonsA, conditionalsLessonsA)
                        } else if(currentLevelScore >= 12 && currentLevelScore<21) {
                            lessonsList = listOf(verbsLessonsB, nounsLessonsB, pronounsLessonsB, determinersLessonsB, conditionalsLessonsB)
                        } else {
                            lessonsList = listOf(verbsLessonsC, nounsLessonsC, pronounsLessonsC, determinersLessonsC, conditionalsLessonsC)
                        }
                    }

                    view.findViewById<RecyclerView>(R.id.units_rv).apply {
                        layoutManager = LinearLayoutManager(this@LearnFragment.context)
                        adapter = UnitRecyclerAdapter(units, lessonsList, fragmentManager)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents.", exception)
                }

        return view
    }
}