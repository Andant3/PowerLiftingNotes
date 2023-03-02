package com.example.powerliftingnotes.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.powerliftingnotes.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var exerciseListAdapter: ExerciseListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
        viewModel.exerciseList.observe(this){
            exerciseListAdapter.submitList(it)
        }
        val btnAddExercise = findViewById<FloatingActionButton>(R.id.btn_add_exercise)
        btnAddExercise.setOnClickListener {
            val intent = ExerciseActivity.newIntentAddExercise(this)
            startActivity(intent)
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
                val exercise = exerciseListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.removeExercise(exercise)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvExerciseList)
    }

    private fun setUpClickListener() {
        exerciseListAdapter.onExerciseClickListener = {
            Log.d("OnClick", it.name)
            val intent = ExerciseActivity.newIntentEditExercise(this, it.id)
            startActivity(intent)
        }
    }

    private fun setUpLongClickListener() {
        exerciseListAdapter.onExerciseLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }
}