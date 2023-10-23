package com.example.radd_e_qadyyani.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.radd_e_qadyyani.Model.QuestionAnswer
import com.example.radd_e_qadyyani.R

class HomeAdapter(private val dataSet: ArrayList<String>): RecyclerView.Adapter<HomeAdapter.ViewHolder>()  {

    var onItemClick: ((position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataSet[position]
        holder.qCardView.setOnClickListener {
            Log.d("TAG", "onBindViewHolder: $position")
            onItemClick?.invoke(position)
        }
    }

    override fun getItemCount(): Int {
        Log.d("TAG", "getItemCount: "+dataSet.size)
        return  dataSet.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.questionTV)
        val qCardView: CardView = view.findViewById(R.id.questionCardView)
    }
}