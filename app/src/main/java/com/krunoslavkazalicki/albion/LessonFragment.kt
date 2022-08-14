package com.krunoslavkazalicki.albion

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class LessonFragment(val lessonKey: String) : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        val view = inflater.inflate(R.layout.fragment_lesson, container, false)
        val lessonTextView: TextView = view.findViewById(R.id.lesson_tv)

        val db = Firebase.firestore
        db.collection("Units/Verbs/${lessonKey}")
                .get()
                .addOnSuccessListener { result ->

                    for (document in result){
                        var defintion = document.data.get("defintion").toString()

                        lessonTextView.text = defintion
                    }

                }
                .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents.", exception)
                }

        return view
    }


}