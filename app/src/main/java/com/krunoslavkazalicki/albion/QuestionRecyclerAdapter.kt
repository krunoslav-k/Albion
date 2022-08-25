package com.krunoslavkazalicki.albion

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuestionRecyclerAdapter(private var questions: List<Question>, private val editor: SharedPreferences.Editor?): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return QuestionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false), editor)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is QuestionViewHolder -> {
                holder.bind(questions[position])
                holder.checkAnswers(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    class QuestionViewHolder(itemView: View, private val editor: SharedPreferences.Editor?): RecyclerView.ViewHolder(itemView){
        private val questionTextTextView: TextView = itemView.findViewById(R.id.questionText_tv)
        private val answersRadioGroup: RadioGroup = itemView.findViewById(R.id.answers_rg)
        private val firstAnswerRadioButton: RadioButton = itemView.findViewById(R.id.firstAnswer_rb)
        private val secondAnswerRadioButton: RadioButton = itemView.findViewById(R.id.secondAnswer_rb)
        private val thirdAnswerRadioButton: RadioButton = itemView.findViewById(R.id.thirdAnswer_rb)
        private val fourthAnswerRadioButton: RadioButton = itemView.findViewById(R.id.fourthAnswer_rb)

        fun bind(question: Question){
            questionTextTextView.text = question.questionText
            firstAnswerRadioButton.text = question.answers[0]
            secondAnswerRadioButton.text = question.answers[1]
            thirdAnswerRadioButton.text = question.answers[2]
            fourthAnswerRadioButton.text = question.answers[3]
        }

        fun checkAnswers(position: Int){
            answersRadioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
                val radio = itemView.findViewById<RadioButton>(checkedId)

                when (radio) {
                    firstAnswerRadioButton -> {
                        editor.apply {
                            this?.putString("answer${position}", "${firstAnswerRadioButton.text.toString()}")
                        }?.apply()
                    }
                    secondAnswerRadioButton -> {
                        editor.apply {
                            this?.putString("answer${position}", "${secondAnswerRadioButton.text.toString()}")
                        }?.apply()
                    }
                    thirdAnswerRadioButton -> {
                        editor.apply {
                            this?.putString("answer${position}", "${thirdAnswerRadioButton.text.toString()}")
                        }?.apply()
                    }
                    fourthAnswerRadioButton -> {
                        editor.apply {
                            this?.putString("answer${position}", "${fourthAnswerRadioButton.text.toString()}")
                        }?.apply()
                    }
                }

            }
        }

    }
}