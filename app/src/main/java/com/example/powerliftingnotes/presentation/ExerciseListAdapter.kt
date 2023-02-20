package com.example.powerliftingnotes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.powerliftingnotes.R
import com.example.powerliftingnotes.domain.Exercise

class ExerciseListAdapter : RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>() {

    var exerciseList = listOf<Exercise>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    val EXERCISE_ENABLED = 1
    val EXERCISE_DISABLED = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val layout = when(viewType){
            EXERCISE_ENABLED -> R.layout.item_exercise_enabled
            EXERCISE_DISABLED -> R.layout.item_exercise_disabled
            else->{
                throw RuntimeException("Unknown ViewType")
            }
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ExerciseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        val exercise = exerciseList[position]

        holder.tvName.text = "${exercise.name}"
        holder.tvWeight.text = exercise.weight.toString()
        holder.tvReps.text = exercise.reps.toString()
        holder.view.setOnLongClickListener {
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        val exercise = exerciseList[position]
        return if(exercise.enabled){
            EXERCISE_ENABLED
        }else{
            EXERCISE_DISABLED
        }
    }
    override fun getItemCount(): Int {
        return exerciseList.size
    }

    class ExerciseViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvWeight = view.findViewById<TextView>(R.id.tv_weight)
        val tvReps = view.findViewById<TextView>(R.id.tv_reps)
    }

    companion object{
        const val EXERCISE_ENABLED = 1
        const val EXERCISE_DISABLED = 0

        const val MAX_POOL_SIZE = 20
    }
}