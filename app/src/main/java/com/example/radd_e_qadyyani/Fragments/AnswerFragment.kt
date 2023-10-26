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
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.radd_e_qadyyani.Adapter.AnswerAdapter
import com.example.radd_e_qadyyani.R

class AnswerFragment : Fragment() {

    var isShareOptionsVisible = false
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

        val answerAdapter = dataset?.let { AnswerAdapter(it) }

        val recyclerView: RecyclerView = view.findViewById(R.id.answerRV)
        val backButton: ImageView = view.findViewById(R.id.backButton)
        val shareButton: ImageView = view.findViewById(R.id.shareButton)
        val titleTV: TextView = view.findViewById(R.id.answerTitleTV)
        val sharell: LinearLayout = view.findViewById(R.id.shareOptionll2)
        val copyIcon: ImageView = view.findViewById(R.id.copyIcon2)
        val shareIcon: ImageView = view.findViewById(R.id.shareIcon2)
        titleTV.text = title
        backButton.setOnClickListener{
            findNavController().navigateUp()
        }
        shareButton.setOnClickListener{
            if (sharell.visibility == View.VISIBLE) {
                sharell.visibility = View.GONE
            } else {
                sharell.visibility = View.VISIBLE
            }
        }
        copyIcon.setOnClickListener {
            if (dataset != null) {
                copyClipBoard(removeHtmlTags(dataset.joinToString(separator = "\n")))
            }
        }
        shareIcon.setOnClickListener {
            if (dataset != null) {
                shareAction(removeHtmlTags(dataset.joinToString(separator = "\n")))
            }
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
        val normalText = input.replace(Regex("<.*?>"), "")
        return   normalText.replace("&[^;]*;".toRegex(), " ")
    }
}