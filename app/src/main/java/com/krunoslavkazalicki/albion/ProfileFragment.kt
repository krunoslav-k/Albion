package com.krunoslavkazalicki.albion

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val db = Firebase.firestore

        db.collection("Results")
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { result ->
                var results: MutableList<Result> = ArrayList<Result>()

                for (document in result) {
                    results.add(Result(
                            document.data.get("result").toString().toInt(),
                            (document.data.get("timestamp") as Timestamp).toDate()))
                }

                view.findViewById<RecyclerView>(R.id.results_rv).apply {
                    layoutManager = LinearLayoutManager(this@ProfileFragment.context)
                    adapter = ResultRecyclerAdapter(results)
                }

            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }

        return view
    }

}