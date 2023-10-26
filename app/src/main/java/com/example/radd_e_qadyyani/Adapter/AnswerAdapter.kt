package com.example.radd_e_qadyyani.Adapter

import android.graphics.Color
import android.graphics.text.LineBreaker
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.opengl.Visibility
import android.text.Html
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.radd_e_qadyyani.R

class AnswerAdapter(private val dataSet: ArrayList<String>): RecyclerView.Adapter<AnswerAdapter.ViewHolder>()  {

    var onShareClick: ((position: Int) -> Unit)? = null
    var onCopyClick: ((position: Int) -> Unit)? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = itemView.findViewById(R.id.answerTV)
        private val webTextView: TextView = itemView.findViewById(R.id.answerWbTV)
        private val shareIcon: ImageView = itemView.findViewById(R.id.shareIcon)
        private val copyIcon: ImageView = itemView.findViewById(R.id.copyIcon)
        private val itemll: LinearLayout = itemView.findViewById(R.id.shareOptionll)

        init {
        }

        fun bind(data: String, position: Int, onShareClick: ((position: Int) -> Unit)? = null, onCopyClick: ((position: Int) -> Unit)? = null) {
            val spannedText = Html.fromHtml(data, 0)
            webTextView.visibility = View.GONE
            textView.visibility = View.VISIBLE
            textView.text = spannedText
            textView.setOnClickListener {
                if (itemll.visibility == View.GONE) {
                    itemll.visibility = View.VISIBLE
                } else {
                    itemll.visibility = View.GONE
                }
            }
            shareIcon.setOnClickListener {
                onShareClick?.invoke(position)
            }
            copyIcon.setOnClickListener {
                onCopyClick?.invoke(position)
            }
        }

        fun bindWebText(data: String, position: Int, onShareClick: ((position: Int) -> Unit)? = null, onCopyClick: ((position: Int) -> Unit)? = null) {
            val spannedText = Html.fromHtml(data, 0)
            webTextView.visibility = View.VISIBLE
            textView.visibility = View.GONE
            webTextView.text = spannedText
            webTextView.setOnClickListener {
                if (itemll.visibility == View.GONE) {
                    itemll.visibility = View.VISIBLE
                } else {
                    itemll.visibility = View.GONE
                }
            }
            shareIcon.setOnClickListener {
                onShareClick?.invoke(position)
            }
            copyIcon.setOnClickListener {
                onCopyClick?.invoke(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.answer_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var text = dataSet[position]
        if (!text.contains("<p>")) {
            text = "<p>$text</p>"
        }
        if (text.contains("www.") || text.contains("https:")) {
            holder.bindWebText(text, position, onShareClick, onCopyClick)
        } else {
            holder.bind(text, position, onShareClick, onCopyClick)
        }
    }

    override fun getItemCount(): Int {
        return  dataSet.size
    }
}