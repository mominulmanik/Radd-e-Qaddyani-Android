package com.example.radd_e_qadyyani.Fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.radd_e_qadyyani.Adapter.AnswerAdapter
import com.example.radd_e_qadyyani.R

class AnswerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_answer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString("questionTitle", "")
        val dataset = arguments?.getStringArrayList("answerList")

        val answerAdapter = dataset?.let { AnswerAdapter(it, title?.contains("বিভাগ") == true) }

        val recyclerView: RecyclerView = view.findViewById(R.id.answerRV)
        val backButton: ImageView = view.findViewById(R.id.backButton)
        val titleTV: TextView = view.findViewById(R.id.answerTitleTV)
        titleTV.text = title
        backButton.setOnClickListener{
            findNavController().navigateUp()
        }
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = answerAdapter
        if (answerAdapter != null) {
            answerAdapter.onShareClick = {
                shareAction(removeHtmlTags(dataset[it]))
            }
        }
        if (answerAdapter != null) {
            answerAdapter.onCopyClick = {
                copyClipBoard(removeHtmlTags(dataset[it]))
            }
        }
    }

    private fun shareAction(msgContent: String) {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, msgContent);
        startActivity(Intent.createChooser(shareIntent, "Share Text"))
    }

    private fun copyClipBoard(msgContent: String) {
        val clipboardManager =
            requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText(
            "text",
            msgContent
        )
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(requireContext(), "Copied", Toast.LENGTH_SHORT).show()
    }

    fun removeHtmlTags(input: String): String {
        return input.replace(Regex("<.*?>"), "")
    }
}