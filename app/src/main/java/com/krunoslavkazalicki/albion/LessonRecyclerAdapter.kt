package com.krunoslavkazalicki.albion

import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView


class LessonRecyclerAdapter(private var lessons: List<String>, val fragmentManager: FragmentManager?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LessonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lesson_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is LessonRecyclerAdapter.LessonViewHolder -> {
                holder.bind(lessons[position])
                holder.itemView.setOnClickListener {
                    holder.startLessonFragment(lessons[position], fragmentManager)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return lessons.size
    }

    class LessonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val lessonNameTextView: TextView = itemView.findViewById(R.id.lessonName_tv)

        fun bind(lesson: String){
            lessonNameTextView.text = lesson
        }

        fun startLessonFragment(lessonKey: String, fragmentManager: FragmentManager?){
            val lessonFragment = LessonFragment(lessonKey)
            val fragmentTransaction: FragmentTransaction? = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment, lessonFragment)?.commit()

        }

    }
}