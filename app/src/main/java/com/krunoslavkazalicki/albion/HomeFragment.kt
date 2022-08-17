package com.krunoslavkazalicki.albion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val takeTestButton: Button = view.findViewById(R.id.takeTest_btn)
        val continueLearningButton: Button = view.findViewById(R.id.continueLearning_btn)

        takeTestButton.setOnClickListener {
            val fragment = TestFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment, fragment)?.commit()
        }

        continueLearningButton.setOnClickListener {
            val fragment = LearnFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment, fragment)?.commit()
        }

        return view
    }
}