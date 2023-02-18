package com.example.powerliftingnotes.domain

class EditExerciseUseCase(private val exerciseListRepository: ExerciseListRepository) {

    fun editExercise(exerciseId: Int){
        exerciseListRepository.editExercise(exerciseId)
    }
}