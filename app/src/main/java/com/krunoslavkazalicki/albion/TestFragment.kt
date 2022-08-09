package com.krunoslavkazalicki.albion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TestFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_test, container, false)
        val finishTestButton: Button = view.findViewById(R.id.finishTest_btn)

        val answears1: List<String> = listOf("future I", "future II", "present simple", "past perfect")
        val answears2: List<String> = listOf("future I", "future II", "present simple", "past perfect")
        val answears3: List<String> = listOf("future I", "future II", "present simple", "past perfect")
        val question1 = Question("I will look into it", answears1, "future I")
        val question2 = Question("I will look into it", answears2, "future I")
        val question3 = Question("I will look into it", answears3, "future I")
        val questions: List<Question> = listOf(question1, question2, question3)

        view.findViewById<RecyclerView>(R.id.questions_rv).apply {
            layoutManager = LinearLayoutManager(this@TestFragment.context)
            adapter = QuestionRecyclerAdapter(questions)
        }

        finishTestButton.setOnClickListener {

        }

        return view
    }


}