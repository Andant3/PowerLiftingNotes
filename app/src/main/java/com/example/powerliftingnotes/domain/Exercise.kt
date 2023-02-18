package com.example.powerliftingnotes.domain

data class Exercise(
    val id: Int,
    val name: String,
    val weight: Int,
    val reps: Int,
    val enabled: Boolean
)
