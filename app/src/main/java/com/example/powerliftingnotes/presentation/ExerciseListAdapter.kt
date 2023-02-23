package com.example.powerliftingnotes.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.powerliftingnotes.R
import com.example.powerliftingnotes.domain.Exercise

class ExerciseListAdapter : ListAdapter<Exercise, ExerciseViewHolder>(ExerciseDiffCallback()) {

    var onExerciseLongClickListener: ((Exercise) -> Unit)? = null
    var onExerciseClickListener: ((Exercise) -> Unit)? = null

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
        val exercise = getItem(position)

        holder.tvName.text = exercise.name
        holder.tvWeight.text = exercise.weight.toString()
        holder.tvReps.text = exercise.reps.toString()
        holder.view.setOnLongClickListener {
            onExerciseLongClickListener?.invoke(exercise)
            true
        }
        holder.view.setOnClickListener{
            onExerciseClickListener?.invoke(exercise)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val exercise = getItem(position)
        return if(exercise.enabled){
            EXERCISE_ENABLED
        }else{
            EXERCISE_DISABLED
        }
    }

    companion object{
        const val EXERCISE_ENABLED = 1
        const val EXERCISE_DISABLED = 0

        const val MAX_POOL_SIZE = 20
    }
}