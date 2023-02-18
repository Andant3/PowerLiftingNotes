package com.example.powerliftingnotes.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.powerliftingnotes.domain.Exercise
import com.example.powerliftingnotes.domain.ExerciseListRepository
import kotlin.system.exitProcess

object ExerciseListRepositoryImpl: ExerciseListRepository {

    private val exerciseList = sortedSetOf<Exercise>({o1, o2 -> o1.id.compareTo(o2.id)})

    private val exerciseListLD = MutableLiveData<List<Exercise>>()

    private var autoIncrementId = 0

    init {
        for (i in 0..20){
            val item = Exercise("$i exercise", i, i, true)
            addExercise(item)
        }
    }

    override fun getExerciseById(exerciseId: Int): Exercise {
        return exerciseList.find{
            it.id == exerciseId
        } ?: throw java.lang.RuntimeException("Element with id $exerciseId not found")
    }

    override fun editExercise(exercise: Exercise) {
        val oldElement = getExerciseById(exercise.id)
        exerciseList.remove(oldElement)
        addExercise(exercise)
    }

    override fun addExercise(exercise: Exercise) {
        if(exercise.id == Exercise.UNDEFINED_ID){
            exercise.id = autoIncrementId++
        }
        exerciseList.add(exercise)
        updateList()
    }

    override fun removeExercise(exercise: Exercise) {
        exerciseList.remove(exercise)
        updateList()
    }

    override fun getExerciseList(): LiveData<List<Exercise>> {
        return exerciseListLD
    }

    private fun updateList(){
        exerciseListLD.value = exerciseList.toList()
    }
}