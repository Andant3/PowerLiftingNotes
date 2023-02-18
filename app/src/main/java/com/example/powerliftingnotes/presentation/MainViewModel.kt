package com.example.powerliftingnotes.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.powerliftingnotes.data.ExerciseListRepositoryImpl
import com.example.powerliftingnotes.domain.*

class MainViewModel: ViewModel() {

    val repository = ExerciseListRepositoryImpl

    val getExerciseListUseCase = GetExerciseListUseCase(repository)
    val removeExerciseUseCase = RemoveExerciseUseCase(repository)
    val editExerciseUseCase = EditExerciseUseCase(repository)

    val exerciseList = getExerciseListUseCase.getExerciseList()


    fun removeExercise(exercise: Exercise){
        removeExerciseUseCase.removeExercise(exercise)
    }

    fun changeEnableState(exercise: Exercise){
        var newExercise = exercise.copy(enabled = !exercise.enabled)
        editExerciseUseCase.editExercise(newExercise)
    }
}