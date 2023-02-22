package com.example.powerliftingnotes.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.powerliftingnotes.domain.Exercise

class ExerciseDiffCallback: DiffUtil.ItemCallback<Exercise>() {


    override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
        return oldItem == newItem
    }
}