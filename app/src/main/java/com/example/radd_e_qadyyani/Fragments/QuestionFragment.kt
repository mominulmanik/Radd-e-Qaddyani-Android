package com.example.radd_e_qadyyani.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.radd_e_qadyyani.Adapter.AnswerAdapter
import com.example.radd_e_qadyyani.Adapter.QuestionAdapter
import com.example.radd_e_qadyyani.Model.QuestionAnswer
import com.example.radd_e_qadyyani.R
import com.example.radd_e_qadyyani.Utils.Constant

class QuestionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString("questionTitle", "")
        val dataset: ArrayList<QuestionAnswer> = arguments?.getSerializable("questionList") as ArrayList<QuestionAnswer>

        val questionAdapter = dataset?.let { QuestionAdapter(it) }

        val recyclerView: RecyclerView = view.findViewById(R.id.questionsRV)
        val titleTV: TextView = view.findViewById(R.id.questionTitleTV)
        val backButton: ImageView = view.findViewById(R.id.backButton2)

        titleTV.text = title
        backButton.setOnClickListener{
            findNavController().navigateUp()
        }
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = questionAdapter
        questionAdapter?.onItemClick = {
            requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                R.id.questionFragment_aAction1,
                Bundle().apply {
                    putStringArrayList("answerList", dataset[it].answer)
                    putString("questionTitle", dataset[it].question)
                })
        }
    }
}