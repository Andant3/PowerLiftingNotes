package com.example.powerliftingnotes.domain

interface ExerciseListRepository {

    fun getExerciseById(exerciseId: Int): Exercise

    fun editExercise(exercise: Exercise)

    fun addExercise(exercise: Exercise)

    fun removeExercise(exercise: Exercise)

    fun getExerciseList(): List<Exercise>
}