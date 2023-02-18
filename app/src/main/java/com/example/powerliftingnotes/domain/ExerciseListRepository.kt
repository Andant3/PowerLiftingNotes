package com.example.powerliftingnotes.domain

interface ExerciseListRepository {

    fun getExerciseById(exerciseId: Int): Exercise

    fun editExercise(exerciseId: Int)

    fun addExercise(exercise: Exercise)

    fun removeExercise(exerciseId: Int)

    fun getExerciseList(): List<Exercise>
}