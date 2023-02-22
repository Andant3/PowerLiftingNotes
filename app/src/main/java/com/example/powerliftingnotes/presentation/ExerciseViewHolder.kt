package com.example.powerliftingnotes.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.powerliftingnotes.R


class ExerciseViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.tv_name)
    val tvWeight = view.findViewById<TextView>(R.id.tv_weight)
    val tvReps = view.findViewById<TextView>(R.id.tv_reps)
}