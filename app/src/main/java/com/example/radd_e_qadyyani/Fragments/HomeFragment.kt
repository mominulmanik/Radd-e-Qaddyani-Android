package com.example.radd_e_qadyyani.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.radd_e_qadyyani.Adapter.QuestionAdapter
import com.example.radd_e_qadyyani.R
import com.example.radd_e_qadyyani.Utils.Constant

class HomeFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val questionAdapter = QuestionAdapter(Constant().mainTopics)

        val recyclerView: RecyclerView = view.findViewById(R.id.questionRV)
        recyclerView.adapter = questionAdapter
        questionAdapter.onItemClick = {
            requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                R.id.homeFragment_aAction,
                Bundle().apply {
                    putStringArrayList("answerList", Constant().mainTopic1)
                })

        }
    }
}