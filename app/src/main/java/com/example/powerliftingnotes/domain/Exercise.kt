package com.example.powerliftingnotes.domain

data class Exercise(
    val name: String,
    val weight: Int,
    val reps: Int,
    val enabled: Boolean,
    var id: Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}
