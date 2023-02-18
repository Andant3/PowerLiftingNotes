package com.example.powerliftingnotes.domain

class GetExerciseUseCase(private val exerciseListRepository: ExerciseListRepository) {

    fun getExerciseById(exerciseId: Int): Exercise{
        return exerciseListRepository.getExerciseById(exerciseId)
    }
}