package com.example.radd_e_qadyyani.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.radd_e_qadyyani.R

class QuestionAdapter(private val dataSet: ArrayList<String>): RecyclerView.Adapter<QuestionAdapter.ViewHolder>()  {

    var onItemClick: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataSet[position]
        holder.qCardView.setOnClickListener {
            onItemClick?.invoke()
        }
    }

    override fun getItemCount(): Int {
        return  dataSet.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val qCardView: CardView

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.questionTV)
            qCardView = view.findViewById(R.id.questionCardView)
        }
    }
}