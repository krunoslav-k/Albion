package com.krunoslavkazalicki.albion

import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView

class QuestionAdapter(private val questions: ArrayList<Question>) : RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val questionText

    }
}