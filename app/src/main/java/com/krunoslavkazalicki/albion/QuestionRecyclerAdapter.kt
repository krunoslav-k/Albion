package com.krunoslavkazalicki.albion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuestionRecyclerAdapter(private var questions: List<Question>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return QuestionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is QuestionViewHolder -> {
                holder.bind(questions[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    class QuestionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val questionTextTextView: TextView = itemView.findViewById(R.id.questionText_tv)
        private val answearsRadioGroup: RadioGroup = itemView.findViewById(R.id.answears_rg)
        private val firstAnswearRadioButton: RadioButton = itemView.findViewById(R.id.firstAnswear_rb)
        private val secondAnswearRadioButton: RadioButton = itemView.findViewById(R.id.secondAnswear_rb)
        private val thirdAnswearRadioButton: RadioButton = itemView.findViewById(R.id.thirdAnswear_rb)
        private val fourthAnswearRadioButton: RadioButton = itemView.findViewById(R.id.fourthAnswear_rb)

        fun bind(question: Question){
            questionTextTextView.text = question.questionText
            firstAnswearRadioButton.text = question.answears[0]
            secondAnswearRadioButton.text = question.answears[1]
            thirdAnswearRadioButton.text = question.answears[2]
            fourthAnswearRadioButton.text = question.answears[3]
        }
    }
}