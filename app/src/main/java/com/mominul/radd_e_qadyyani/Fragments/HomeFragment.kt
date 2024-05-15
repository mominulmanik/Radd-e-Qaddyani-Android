package com.mominul.radd_e_qadyyani.Fragments

import android.annotation.SuppressLint
import android.content.res.AssetManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mominul.radd_e_qadyyani.Adapter.HomeAdapter
import com.mominul.radd_e_qadyyani.Model.QuestionAnswerList
import com.mominul.radd_e_qadyyani.R
import com.mominul.radd_e_qadyyani.Utils.Constant
import com.google.gson.Gson
import com.mominul.radd_e_qadyyani.Adapter.QuestionAdapter

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

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.questionRV)
        recyclerView.setHasFixedSize(true);
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val qaList = readDataFromJson("qadyyani_test.json")
        val questionAdapter = qaList?.let { QuestionAdapter(it.questions) }
        recyclerView.adapter = questionAdapter
        questionAdapter?.onItemClick = {
            if (qaList?.questions?.get(it)?.answer == null) {
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_qAction1,
                    Bundle().apply {
                        putSerializable("questionList", qaList?.questions?.get(it)?.questions)
                        putString("questionTitle", qaList?.questions?.get(it)?.question)
                    })
            } else {
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_aAction1,
                    Bundle().apply {
                        putStringArrayList("answerList", qaList.questions[it].answer)
                        putString("questionTitle", qaList.questions[it].question)
                    })
            }
        }
    }

    private fun AssetManager.readAssetsFile(fileName: String): String =
        open(fileName).bufferedReader().use { it.readText() }

    fun readDataFromJson(fileName: String): QuestionAnswerList? {
        var jsonContent = requireActivity().assets.readAssetsFile(fileName)
        jsonContent = jsonContent.replace("\n", "")
        return try {
            Gson().fromJson(jsonContent, QuestionAnswerList::class.java)
        } catch (e: Exception) {
            null
        }
    }

    fun navigateToQuestionScreen(qaList: QuestionAnswerList?, title: String) {
        requireActivity().findNavController(R.id.nav_host_fragment).navigate(
            R.id.homeFragment_qAction1,
            Bundle().apply {
                putSerializable("questionList", qaList?.questions)
                putString("questionTitle", title)
            })
    }

    fun navigateToAnswerScreen(answerList: ArrayList<String>, title: String) {
        requireActivity().findNavController(R.id.nav_host_fragment).navigate(
            R.id.homeFragment_aAction1,
            Bundle().apply {
                putSerializable("answerList", answerList)
                putString("questionTitle", title)
            })
    }
}