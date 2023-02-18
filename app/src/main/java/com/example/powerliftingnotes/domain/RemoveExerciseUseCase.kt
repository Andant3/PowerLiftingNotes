package com.example.powerliftingnotes.domain

class RemoveExerciseUseCase(private val exerciseListRepository: ExerciseListRepository) {

    fun removeExercise(exercise: Exercise){
        exerciseListRepository.removeExercise(exercise)
    }
}