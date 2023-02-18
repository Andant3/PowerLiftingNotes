package com.example.powerliftingnotes.domain

import androidx.lifecycle.LiveData

class GetExerciseListUseCase(private val exerciseListRepository: ExerciseListRepository) {

    fun getExerciseList(): LiveData<List<Exercise>>{
        return exerciseListRepository.getExerciseList()
    }
}