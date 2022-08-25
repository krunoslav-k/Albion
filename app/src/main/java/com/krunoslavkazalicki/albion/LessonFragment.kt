package com.krunoslavkazalicki.albion

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class LessonFragment(val lessonKey: String) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        val view = inflater.inflate(R.layout.fragment_lesson, container, false)
        val lessonTitleTextView: TextView = view.findViewById(R.id.lessonTitle_tv)
        val lessonIntroTextView: TextView = view.findViewById(R.id.lessonIntro_tv)
        val lessonFormTextView: TextView = view.findViewById(R.id.lessonForm_tv)
        val lessonUsageTextView: TextView = view.findViewById(R.id.lessonUsage_tv)
        val lessonExamplesTextView: TextView = view.findViewById(R.id.lessonExamples_tv)

        val db = Firebase.firestore

        Toast.makeText(context, "Lesson key: ${lessonKey}", Toast.LENGTH_SHORT).show()
        db.collection("Units/Verbs/${lessonKey.filter { !it.isWhitespace() }}")
                .get()
                .addOnSuccessListener { result ->

                    for (document in result){
                        lessonTitleTextView.text = document.data.get("title").toString()
                        lessonIntroTextView.text = document.data.get("introduction").toString()
                        lessonFormTextView.text = document.data.get("form").toString()
                        lessonUsageTextView.text = document.data.get("usage").toString()
                        lessonExamplesTextView.text = document.data.get("examples").toString()
                    }

                }
                .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents.", exception)
                }

        return view
    }


}