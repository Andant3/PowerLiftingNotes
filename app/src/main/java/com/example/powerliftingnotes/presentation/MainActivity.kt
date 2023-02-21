package com.example.powerliftingnotes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.powerliftingnotes.R
import com.example.powerliftingnotes.domain.Exercise
import java.util.*
import kotlin.concurrent.timerTask

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var exerciseListAdapter: ExerciseListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
        viewModel.exerciseList.observe(this){
            exerciseListAdapter.exerciseList = it
        }
    }
    private fun setUpRecyclerView(){
        val rvExerciseList = findViewById<RecyclerView>(R.id.rv_exercise_list)
        exerciseListAdapter = ExerciseListAdapter()
        with(rvExerciseList) {
            adapter = exerciseListAdapter
            recycledViewPool.setMaxRecycledViews(
                ExerciseListAdapter.EXERCISE_ENABLED,
                ExerciseListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ExerciseListAdapter.EXERCISE_DISABLED,
                ExerciseListAdapter.MAX_POOL_SIZE
            )
        }
        setUpLongClickListener()
        setUpClickListener()
        setUpSwipeListener(rvExerciseList)
    }

    private fun setUpSwipeListener(rvExerciseList: RecyclerView) {
        val callback = object : ItemTouchHelper
        .SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val exercise = exerciseListAdapter.exerciseList[position]
                viewModel.removeExercise(exercise)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvExerciseList)
    }

    private fun setUpClickListener() {
        exerciseListAdapter.onExerciseClickListener = {
            Log.d("OnClick", it.name)
        }
    }

    private fun setUpLongClickListener() {
        exerciseListAdapter.onExerciseLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }
}