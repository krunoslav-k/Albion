package com.krunoslavkazalicki.albion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LessonRecyclerAdapter(private var lessons: List<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LessonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lesson_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is LessonRecyclerAdapter.LessonViewHolder -> {
                holder.bind(lessons[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return lessons.size
    }

    class LessonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val lessonNameTextView: TextView = itemView.findViewById(R.id.lessonName_tv)
        private val lessonImageView: ImageView = itemView.findViewById(R.id.lessonImage_iv)

        fun bind(lesson: String){

        }

    }
}