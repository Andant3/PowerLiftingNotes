package com.example.powerliftingnotes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.powerliftingnotes.data.ExerciseListRepositoryImpl
import com.example.powerliftingnotes.domain.AddExerciseUseCase
import com.example.powerliftingnotes.domain.EditExerciseUseCase
import com.example.powerliftingnotes.domain.Exercise
import com.example.powerliftingnotes.domain.GetExerciseUseCase

class ExerciseViewModel : ViewModel() {

    //Incorrect way for repository realisation
    //(Presentation and Data layers dependency)
    private val repository = ExerciseListRepositoryImpl

    private val _exercise = MutableLiveData<Exercise>()
    val exercise: LiveData<Exercise>
        get() = _exercise

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen


    //Method to appear LiveData variable with access to values for ViewModel
    //and no access for View
    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputWeight = MutableLiveData<Boolean>()
    val errorInputWeight: LiveData<Boolean>
        get() = _errorInputWeight

    private val _errorInputReps = MutableLiveData<Boolean>()
    val errorInputReps: LiveData<Boolean>
        get() = _errorInputReps

    private val getExerciseUseCase = GetExerciseUseCase(repository)
    private val addExerciseUseCase = AddExerciseUseCase(repository)
    private val editExerciseUseCase = EditExerciseUseCase(repository)

    fun getExercise(exerciseId: Int) {
        val exercise = getExerciseUseCase.getExerciseById(exerciseId)
        _exercise.value = exercise
    }

    fun addExercise(inputName: String?, inputWeight: String?, inputReps: String?) {
        val name = parseName(inputName)
        val weight = parseWeight(inputWeight)
        val reps = parseReps(inputReps)
        val fieldsValid = validateInput(name, weight, reps)
        if (fieldsValid) {
            val exercise = Exercise(name, weight, reps, true)
            addExerciseUseCase.addExercise(exercise)
            finishWork()
        }

    }

    fun editExercise(inputName: String?, inputWeight: String?, inputReps: String?) {
        val name = parseName(inputName)
        val weight = parseWeight(inputWeight)
        val reps = parseReps(inputReps)
        val fieldsValid = validateInput(name, weight, reps)
        if (fieldsValid) {
            _exercise.value?.let {
                val exercise = it.copy(name = name, weight = weight, reps = reps)
                editExerciseUseCase.editExercise(exercise)
                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseWeight(inputWeight: String?): Int {
        return try {
            inputWeight?.trim()?.toInt() ?: 0
        } catch (e: NumberFormatException) {
            0
        }
    }

    private fun parseReps(inputReps: String?): Int {
        return try {
            inputReps?.trim()?.toInt() ?: 0
        } catch (e: NumberFormatException) {
            0
        }
    }

    private fun validateInput(name: String, weight: Int, reps: Int): Boolean {
        var result = true

        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (weight <= 0) {
            _errorInputWeight.value = true
            result = false
        }
        if (reps <= 0) {
            _errorInputReps.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputWeight() {
        _errorInputWeight.value = false
    }

    fun resetErrorInputReps() {
        _errorInputReps.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}