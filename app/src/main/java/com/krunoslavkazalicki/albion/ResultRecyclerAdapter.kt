package com.krunoslavkazalicki.albion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResultRecyclerAdapter(private val results: List<Result>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ResultViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.unit_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ResultRecyclerAdapter.ResultViewHolder -> {
                holder.bind(results[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }

    class ResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val numberedResultTextView: TextView = itemView.findViewById(R.id.numberedResult_tv)
        val dateResultTextView: TextView = itemView.findViewById(R.id.dateResult_tv)

        fun bind(result: Result){
            numberedResultTextView.text = "Score: ${result.result}"
            dateResultTextView.text = "Date: ${result.timestamp}"
        }
    }
}