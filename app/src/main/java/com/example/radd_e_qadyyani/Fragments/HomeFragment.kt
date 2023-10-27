package com.example.radd_e_qadyyani.Fragments

import android.annotation.SuppressLint
import android.content.res.AssetManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.radd_e_qadyyani.Adapter.HomeAdapter
import com.example.radd_e_qadyyani.Model.QuestionAnswerList
import com.example.radd_e_qadyyani.R
import com.example.radd_e_qadyyani.Utils.Constant
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
                    R.id.homeFragment_aAction,
                    Bundle().apply {
                        putStringArrayList("answerList", Constant().mainTopic1)
                        putString("questionTitle", mainTopicList[it])
                    })
            } else if (it == 1) {
                val qaList = readDataFromJson("qadyyani2.json")
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_qAction,
                    Bundle().apply {
                        putSerializable("questionList", qaList?.questions)
                        putString("questionTitle", mainTopicList[it])
                    })
            } else if (it == 2) {
                val qaList = readDataFromJson("qadyyani7.json")
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_qAction,
                    Bundle().apply {
                        putSerializable("questionList", qaList?.questions)
                        putString("questionTitle", mainTopicList[it])
                    })
            } else if (it == 3) {
                val qaList = readDataFromJson("qadyyani4.json")
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_qAction,
                    Bundle().apply {
                        putSerializable("questionList", qaList?.questions)
                        putString("questionTitle", mainTopicList[it])
                    })
            } else if (it == 4) {
                val qaList = readDataFromJson("qadyyani5.json")
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_qAction,
                    Bundle().apply {
                        putSerializable("questionList", qaList?.questions)
                        putString("questionTitle", mainTopicList[it])
                    })
            } else if (it == 5) {
                val qaList = readDataFromJson("qadyyani6.json")
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_aAction,
                    Bundle().apply {
                        putSerializable("answerList", qaList?.questions?.get(0)?.answer)
                        putString("questionTitle", mainTopicList[it])
                    })
            } else if (it == 6) {
                val qaList = readDataFromJson("qadyyani9.json")
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_qAction,
                    Bundle().apply {
                        putSerializable("questionList", qaList?.questions)
                        putString("questionTitle", mainTopicList[it])
                    })
            } else if (it == 7) {
                val qaList = readDataFromJson("qadyyani8.json")
                requireActivity().findNavController(R.id.nav_host_fragment).navigate(
                    R.id.homeFragment_qAction,
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
}