package com.example.powerliftingnotes.presentation

import androidx.lifecycle.ViewModel
import com.example.powerliftingnotes.data.ExerciseListRepositoryImpl
import com.example.powerliftingnotes.domain.EditExerciseUseCase
import com.example.powerliftingnotes.domain.Exercise
import com.example.powerliftingnotes.domain.GetExerciseListUseCase
import com.example.powerliftingnotes.domain.RemoveExerciseUseCase

class MainViewModel: ViewModel() {

    //Incorrect way for repository realisation
    //(Presentation and Data layers dependency)
    val repository = ExerciseListRepositoryImpl

    val getExerciseListUseCase = GetExerciseListUseCase(repository)
    val removeExerciseUseCase = RemoveExerciseUseCase(repository)
    val editExerciseUseCase = EditExerciseUseCase(repository)

    val exerciseList = getExerciseListUseCase.getExerciseList()

    fun removeExercise(exercise: Exercise){
        removeExerciseUseCase.removeExercise(exercise)
    }

    fun changeEnableState(exercise: Exercise){
        val newExercise = exercise.copy(enabled = !exercise.enabled)
        editExerciseUseCase.editExercise(newExercise)
    }
}