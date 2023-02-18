package com.example.powerliftingnotes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.powerliftingnotes.R
import com.example.powerliftingnotes.domain.Exercise

class ExerciseListAdapter: RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>() {

    val list = listOf<Exercise>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_exercise_enabled, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = list[position]
        holder.tvName.text = exercise.name
        holder.tvWeight.text = exercise.weight.toString()
        holder.tvReps.text = exercise.reps.toString()
        holder.view.setOnLongClickListener {
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ExerciseViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvWeight = view.findViewById<TextView>(R.id.tv_weight)
        val tvReps = view.findViewById<TextView>(R.id.tv_reps)
    }
}