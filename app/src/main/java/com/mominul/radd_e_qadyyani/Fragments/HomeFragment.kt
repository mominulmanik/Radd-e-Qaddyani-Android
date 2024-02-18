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
        val mainTopicList = Constant().mainTopics
        val homeAdapter = HomeAdapter(mainTopicList)

        val recyclerView: RecyclerView = view.findViewById(R.id.questionRV)
        recyclerView.setHasFixedSize(true);
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = homeAdapter
        homeAdapter.onItemClick = {
            if (it == 0) {
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_aAction1,
                    Bundle().apply {
                        putStringArrayList("answerList", Constant().mainTopic1)
                        putString("questionTitle", mainTopicList[it])
                    })
            } else if (it == 1) {
                val ansList = readDataFromJson("qadyyani13.json")
                ansList?.questions?.get(0)?.answer?.let { it1 -> navigateToAnswerScreen(answerList = it1, mainTopicList[it]) }
            } else if (it == 2) {
                navigateToAnswerScreen(answerList = Constant().mainTopic2, mainTopicList[it])
            } else if (it == 3) {
                val qaList = readDataFromJson("qadyyani2.json")
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_qAction1,
                    Bundle().apply {
                        putSerializable("questionList", qaList?.questions)
                        putString("questionTitle", mainTopicList[it])
                    })
            } else if (it == 4) {
                val ansList = readDataFromJson("qadyyani14.json")
                ansList?.questions?.get(0)?.answer?.let { it1 -> navigateToAnswerScreen(answerList = it1, mainTopicList[it]) }
            } else if (it == 5) {
                val qaList = readDataFromJson("qadyyani7.json")
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_qAction1,
                    Bundle().apply {
                        putSerializable("questionList", qaList?.questions)
                        putString("questionTitle", mainTopicList[it])
                    })
            } else if (it == 6) {
                val qaList = readDataFromJson("qadyyani4.json")
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_qAction1,
                    Bundle().apply {
                        putSerializable("questionList", qaList?.questions)
                        putString("questionTitle", mainTopicList[it])
                    })
            } else if (it == 7) {
                val ansList = readDataFromJson("qadyyani10.json")
                ansList?.questions?.get(0)?.answer?.let { it1 -> navigateToAnswerScreen(answerList = it1, mainTopicList[it]) }
            } else if (it == 8) {
                val ansList = readDataFromJson("qadyyani11.json")
                ansList?.questions?.get(0)?.answer?.let { it1 -> navigateToAnswerScreen(answerList = it1, mainTopicList[it]) }
            } else if (it == 9) {
                val ansList = readDataFromJson("qadyyani15.json")
                ansList?.questions?.get(0)?.answer?.let { it1 -> navigateToAnswerScreen(answerList = it1, mainTopicList[it]) }
            } else if (it == 10) {
                val ansList = readDataFromJson("qadyyani16.json")
                ansList?.questions?.get(0)?.answer?.let { it1 -> navigateToAnswerScreen(answerList = it1, mainTopicList[it]) }
            } else if (it == 11) {
                val ansList = readDataFromJson("qadyyani17.json")
                ansList?.questions?.get(0)?.answer?.let { it1 -> navigateToAnswerScreen(answerList = it1, mainTopicList[it]) }
            } else if (it == 12) {
                val ansList = readDataFromJson("qadyyani18.json")
                ansList?.questions?.get(0)?.answer?.let { it1 -> navigateToAnswerScreen(answerList = it1, mainTopicList[it]) }
            } else if (it == 13) {
                val qaList = readDataFromJson("qadyyani5.json")
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_qAction1,
                    Bundle().apply {
                        putSerializable("questionList", qaList?.questions)
                        putString("questionTitle", mainTopicList[it])
                    })
            } else if (it == 14) {
                val qaList = readDataFromJson("qadyyani6.json")
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_aAction1,
                    Bundle().apply {
                        putSerializable("answerList", qaList?.questions?.get(0)?.answer)
                        putString("questionTitle", mainTopicList[it])
                    })
            } else if (it == 15) {
                val qaList = readDataFromJson("qadyyani12.json")
                navigateToQuestionScreen(qaList = qaList, mainTopicList[it])
            } else if (it == 16) {
                val qaList = readDataFromJson("qadyyani9.json")
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_aAction1,
                    Bundle().apply {
                        putStringArrayList("answerList", qaList?.questions?.get(0)?.answer)
                        putString("questionTitle", mainTopicList[it])
                    })
            } else if (it == 17) {
                navigateToAnswerScreen(answerList = Constant().mainTopic3, mainTopicList[it])
            } else if (it == 18) {
                val qaList = readDataFromJson("qadyyani8.json")
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_qAction1,
                    Bundle().apply {
                        putSerializable("questionList", qaList?.questions)
                        putString("questionTitle", mainTopicList[it])
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
                putSerializable("questionList", answerList)
                putString("questionTitle", title)
            })
    }
}