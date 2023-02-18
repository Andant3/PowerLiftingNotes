package com.example.powerliftingnotes.domain

class AddExerciseUseCase(private val exerciseListRepository: ExerciseListRepository) {

    fun addExercise(exercise: Exercise){
        exerciseListRepository.addExercise(exercise)
    }
}