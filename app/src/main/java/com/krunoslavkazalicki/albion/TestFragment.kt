package com.krunoslavkazalicki.albion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TestFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_test, container, false)

        val answears: List<String> = listOf("future I", "future II", "present simple", "past perfect")
        val question = Question("I will look into it", answears, "future I")
        val questions: List<Question> = listOf(question)

        view.findViewById<RecyclerView>(R.id.questions_rv).apply {
            layoutManager = LinearLayoutManager(this@TestFragment.context)
            adapter = QuestionRecyclerAdapter(questions)
        }

        return view
    }


}