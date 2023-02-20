package com.example.powerliftingnotes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.powerliftingnotes.R
import com.example.powerliftingnotes.domain.Exercise

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ExerciseListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
        viewModel.exerciseList.observe(this){
            adapter.exerciseList = it
        }
    }
    private fun setUpRecyclerView(){
        val rvExerciseList = findViewById<RecyclerView>(R.id.rv_exercise_list)
        adapter = ExerciseListAdapter()
        rvExerciseList.adapter = adapter
        with(rvExerciseList) {
            recycledViewPool.setMaxRecycledViews(
                ExerciseListAdapter.EXERCISE_ENABLED,
                ExerciseListAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                ExerciseListAdapter.EXERCISE_DISABLED,
                ExerciseListAdapter.MAX_POOL_SIZE
            )
        }
    }
}