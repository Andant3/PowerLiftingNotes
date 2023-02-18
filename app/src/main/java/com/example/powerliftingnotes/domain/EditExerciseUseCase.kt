package com.example.powerliftingnotes.domain

class EditExerciseUseCase(private val exerciseListRepository: ExerciseListRepository) {

    fun editExercise(exercise: Exercise){
        exerciseListRepository.editExercise(exercise)
    }
}