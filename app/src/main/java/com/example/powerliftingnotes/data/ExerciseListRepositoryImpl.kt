package com.example.powerliftingnotes.data

import com.example.powerliftingnotes.domain.Exercise
import com.example.powerliftingnotes.domain.ExerciseListRepository

object ExerciseListRepositoryImpl: ExerciseListRepository {

    private val exerciseList = mutableListOf<Exercise>()

    private var autoIncrementId = 0

    override fun getExerciseById(exerciseId: Int): Exercise {
        return exerciseList.find{
            it.id == exerciseId
        } ?: throw java.lang.RuntimeException("Element with id $exerciseId not found")
    }

    override fun editExercise(exercise: Exercise) {
        val oldElement = exercise
        exerciseList.remove(oldElement)
        exerciseList.add(exercise)
    }

    override fun addExercise(exercise: Exercise) {
        if(exercise.id == Exercise.UNDEFINED_ID){
            exercise.id = autoIncrementId++
        }
        exerciseList.add(exercise)
    }

    override fun removeExercise(exercise: Exercise) {
        exerciseList.remove(exercise)
    }

    override fun getExerciseList(): List<Exercise> {
        return exerciseList.toList()
    }
}