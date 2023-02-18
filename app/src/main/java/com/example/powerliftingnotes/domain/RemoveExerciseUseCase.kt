package com.example.powerliftingnotes.domain

class RemoveExerciseUseCase(private val exerciseListRepository: ExerciseListRepository) {

    fun removeExercise(exerciseId: Int){
        exerciseListRepository.removeExercise(exerciseId)
    }
}