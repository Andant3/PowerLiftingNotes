package com.example.powerliftingnotes.domain

class GetExerciseListUseCase(private val exerciseListRepository: ExerciseListRepository) {

    fun getExerciseList(): List<Exercise>{
        return exerciseListRepository.getExerciseList()
    }
}