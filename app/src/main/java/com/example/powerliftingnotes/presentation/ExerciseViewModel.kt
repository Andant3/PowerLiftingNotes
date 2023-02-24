package com.example.powerliftingnotes.presentation

import androidx.lifecycle.ViewModel
import com.example.powerliftingnotes.data.ExerciseListRepositoryImpl
import com.example.powerliftingnotes.domain.AddExerciseUseCase
import com.example.powerliftingnotes.domain.EditExerciseUseCase
import com.example.powerliftingnotes.domain.Exercise
import com.example.powerliftingnotes.domain.GetExerciseUseCase

class ExerciseViewModel: ViewModel() {

    //Incorrect way for repository realisation
    //(Presentation and Data layers dependency)
    private val repository = ExerciseListRepositoryImpl

    private val getExerciseUseCase = GetExerciseUseCase(repository)
    private val addExerciseUseCase = AddExerciseUseCase(repository)
    private val editExerciseUseCase = EditExerciseUseCase(repository)

    fun getExercise(id: Int){
        val exercise = getExerciseUseCase.getExerciseById(id)
    }

    fun addExercise(inputName: String?, inputWeight: String?, inputReps: String?){
        val name = parseName(inputName)
        val weight = parseWeight(inputWeight)
        val reps = parseReps(inputReps)
        val fieldsValid = validateInput(name, weight, reps)
        if (fieldsValid){
            val exercise = Exercise(name, weight, reps, true)
            addExerciseUseCase.addExercise(exercise)
        }
    }

    fun editExercise(inputName: String?, inputWeight: String?, inputReps: String?){
        val name = parseName(inputName)
        val weight = parseWeight(inputWeight)
        val reps = parseReps(inputReps)
        val fieldsValid = validateInput(name, weight, reps)
        if (fieldsValid){
            val exercise = Exercise(name, weight, reps, true)
            editExerciseUseCase.editExercise(exercise)
        }
    }

    private fun parseName(inputName: String?): String{
        return inputName?.trim() ?: ""
    }

    private fun parseWeight(inputWeight: String?): Int{
        return try {
            inputWeight?.trim()?.toInt() ?: 0
        }catch (e: NullPointerException){
            0
        }
    }

    private fun parseReps(inputReps: String?): Int{
        return try {
            inputReps?.trim()?.toInt() ?: 0
        }catch (e: NullPointerException){
            0
        }
    }

    private fun validateInput(name: String, weight: Int, reps:Int): Boolean{
        var result = true

        if(name.isBlank()){
            // TODO: Show error input name
            result = false
        }
        if(weight <= 0){
            // TODO: Show error input weight
            result = false
        }
        if(reps <= 0){
            // TODO: Show error input reps
            result = false
        }
        return result
    }
}