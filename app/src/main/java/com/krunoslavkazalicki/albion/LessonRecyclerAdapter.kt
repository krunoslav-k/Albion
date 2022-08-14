package com.krunoslavkazalicki.albion

import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView


class LessonRecyclerAdapter(private var lessons: List<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LessonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lesson_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is LessonRecyclerAdapter.LessonViewHolder -> {
                holder.bind(lessons[position])
                holder.itemView.setOnClickListener {
                    var lessonKey = lessons[position]
                    holder.startLessonFragment(lessonKey)
                }
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
            lessonNameTextView.text = lesson
        }

        fun startLessonFragment(lessonKey: String){
            val fragment = LessonFragment(lessonKey)
            val fragmentManager = (itemView.context as AppCompatActivity).supportFragmentManager
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment, fragment)?.commit()
            //ostane novi fragment (trenutno home) preko drugih fragmenata ako se mijenjaju pomoću navigationMenua, ali strelica nazad vraća na početak normalno. Kada klik bude otvaro novi fragment lekcija, strelica će i biti jedino riješenje da se vrati nazad tako da mislim da neće biti problem
            //u HomeFragment(key: String) proslijedimo key ovisno koja je lekcija kliknuta, tako da kada se stvori novi fragment leckije, on točno zna koje informacije će povući iz Firebasea i prikazati
        }

    }
}