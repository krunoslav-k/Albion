package com.krunoslavkazalicki.albion

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class TestResultsFragment : Fragment() {

override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_test_results, container, false)
    val numberResultTextView: TextView = view.findViewById(R.id.numberResult_tv)
    val levelResultTextView: TextView = view.findViewById(R.id.levelResult_tv)
    val goToProfileButton: Button = view.findViewById(R.id.goToProfile_btn)
    val learnButton: Button = view.findViewById(R.id.learn_btn)
    val db = Firebase.firestore

    db.collection("Results")
        .orderBy("timestamp", Query.Direction.DESCENDING).limit(1)
        .get()
        .addOnSuccessListener { result ->
            for (document in result) {
                var score = document.data.get("result").toString()
                var level: String

                if (score.toInt() >= 21) {
                    level = "Advanced" //A
                } else if (score.toInt() >= 12 && score.toInt() < 21) {
                    level = "Intermediate" //B
                } else {
                    level = "Beginner" //C
                }

                numberResultTextView.text = "${score}/30"
                levelResultTextView.text = level
            }
        }
        .addOnFailureListener { exception ->
            Log.w(ContentValues.TAG, "Error getting documents.", exception)
        }

    goToProfileButton.setOnClickListener {
        val fragment = ProfileFragment()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment, fragment)?.commit()
    }

    learnButton.setOnClickListener {
        val fragment = LearnFragment()
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.fragment, fragment)?.commit()
    }

    return view
    }
}