package com.example.radd_e_qadyyani.Adapter

import android.graphics.Color
import android.opengl.Visibility
import android.text.Html
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

class AnswerAdapter(private val dataSet: ArrayList<String>, var isStatistics: Boolean = false): RecyclerView.Adapter<AnswerAdapter.ViewHolder>()  {

    var onShareClick: ((position: Int) -> Unit)? = null
    var onCopyClick: ((position: Int) -> Unit)? = null
    var willShowShareIcon = false

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val shareIcon: ImageView
        val copyIcon: ImageView
        val itemll: LinearLayout

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.answerTV)
            shareIcon = view.findViewById(R.id.shareIcon)
            copyIcon = view.findViewById(R.id.copyIcon)
            itemll = view.findViewById(R.id.shareOptionll)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.answer_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.textView.text = dataSet[position]
        val spannedText = Html.fromHtml(dataSet[position])
        holder.textView.text = spannedText

        // Make the link clickable
        holder.textView.movementMethod = LinkMovementMethod.getInstance()
        holder.textView.setOnClickListener {
            willShowShareIcon = !willShowShareIcon
            if (willShowShareIcon) {
                holder.shareIcon.visibility = View.VISIBLE
                holder.copyIcon.visibility = View.VISIBLE
                holder.itemll.visibility = View.VISIBLE
            } else {
                holder.shareIcon.visibility = View.GONE
                holder.copyIcon.visibility = View.GONE
                holder.itemll.visibility = View.GONE
            }
        }
        holder.shareIcon.setOnClickListener {
            onShareClick?.invoke(position)
        }
        holder.copyIcon.setOnClickListener {
            onCopyClick?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        return  dataSet.size
    }
}