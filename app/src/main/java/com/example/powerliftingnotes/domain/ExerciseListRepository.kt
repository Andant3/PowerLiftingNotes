package com.example.powerliftingnotes.domain

import androidx.lifecycle.LiveData

interface ExerciseListRepository {

    fun getExerciseById(exerciseId: Int): Exercise

    fun editExercise(exercise: Exercise)

    fun addExercise(exercise: Exercise)

    fun removeExercise(exercise: Exercise)

    fun getExerciseList(): LiveData<List<Exercise>>
}