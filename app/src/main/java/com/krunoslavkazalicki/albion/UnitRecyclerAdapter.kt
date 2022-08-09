package com.krunoslavkazalicki.albion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UnitRecyclerAdapter(private var units: List<String>, private var lessons: List<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UnitViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.unit_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is UnitViewHolder -> {
                holder.bind(units[position])
            }
        }

        val lessonRecyclerAdapter = LessonRecyclerAdapter(lessons)

    }

    override fun getItemCount(): Int {
        return units.size
    }

    class UnitViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        private val unitNameTextView: TextView = itemView.findViewById(R.id.unitName_tv)
        val lessonsRecyclerView: RecyclerView = itemView.findViewById(R.id.unitLessons_rv)

        fun bind(unit: String){
            unitNameTextView.text = unit
        }

    }
}