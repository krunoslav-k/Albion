package com.krunoslavkazalicki.albion

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TestFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_test, container, false)
        val sharedPreferences: SharedPreferences? = activity?.getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE)
        val sharedPreferencesEditor: SharedPreferences.Editor? = sharedPreferences?.edit()
        val finishTestButton: Button = view.findViewById(R.id.finishTest_btn)

        val db = Firebase.firestore
        db.collection("TestQuestions")
                .get()
                .addOnSuccessListener { result ->
                    val questions: MutableList<Question> = ArrayList<Question>()

                    for (document in result){
                        var questionText = document.data.get("questionText").toString()
                        var answears: List<String> = document.data.get("answears") as List<String>
                        var correctAnswear = document.data.get("correctAnswear").toString()
                        var question = Question(questionText, answears, correctAnswear)

                        questions.add(question)
                    }

                    view.findViewById<RecyclerView>(R.id.questions_rv).apply {
                        layoutManager = LinearLayoutManager(this@TestFragment.context)
                        adapter = QuestionRecyclerAdapter(questions, sharedPreferencesEditor)
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents.", exception)
                }

        finishTestButton.setOnClickListener {
            var correctAnswearsCount: Int = 0

            db.collection("TestQuestions")
                .get()
                .addOnSuccessListener { result ->
                    var i = 0
                    for(document in result){
                        if((sharedPreferences?.getString("answear${i}", null).equals(document.data.get("correctAnswear").toString(), true))){
                                correctAnswearsCount++
                            }
                        i++
                    }

                    //for testing purposes
                    //Toast.makeText(context, "Correct answears: ${correctAnswearsCount}", Toast.LENGTH_SHORT).show()

                    val result = hashMapOf(
                        "result" to correctAnswearsCount,
                        "timestamp" to getCurrentDateTime()
                    )

                    db.collection("Results")
                        .add(result)
                        .addOnSuccessListener { documentReference ->
                            Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.w(ContentValues.TAG, "Error adding document", e)
                        }
                }

            val fragment = TestResultsFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment, fragment)?.commit()
        }

        return view
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

}