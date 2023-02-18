package com.example.powerliftingnotes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.powerliftingnotes.R
import com.example.powerliftingnotes.domain.Exercise

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var llExerciseList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        llExerciseList = findViewById(R.id.ll_exercise_list)
        viewModel = ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
        viewModel.exerciseList.observe(this){
            showList(it)
        }
    }

    private fun showList(list: List<Exercise>){
        llExerciseList.removeAllViews()
        for (exercise in list){
            val layoutId = if (exercise.enabled){
                R.layout.item_exercise_enabled
            }else{
                R.layout.item_exercise_disabled
            }
           val view = LayoutInflater.from(this)
               .inflate(layoutId, llExerciseList, false)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvWeight = view.findViewById<TextView>(R.id.tv_weight)
            val tvReps = view.findViewById<TextView>(R.id.tv_reps)
            tvName.text = exercise.name
            tvWeight.text = exercise.weight.toString()
            tvReps.text = exercise.reps.toString()
            view.setOnLongClickListener {
                viewModel.changeEnableState(exercise)
                true
            }
            llExerciseList.addView(view)
        }
    }
}