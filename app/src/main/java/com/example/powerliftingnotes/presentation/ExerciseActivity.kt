package com.example.powerliftingnotes.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.powerliftingnotes.R
import com.example.powerliftingnotes.domain.Exercise

class ExerciseActivity : AppCompatActivity() {
    private var screenMode = MODE_UNKNOWN
    private var exerciseId = Exercise.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        parseIntent()
        launchRightMode()
    }

    private fun launchRightMode() {
       val fragment = when (screenMode) {
            MODE_EDIT -> ExerciseFragment.newInstanceEditExercise(exerciseId)
            MODE_ADD -> ExerciseFragment.newInstanceAddExercise()
           else->{
               throw RuntimeException("Unknown screen mode $screenMode")
           }
       }
    supportFragmentManager.beginTransaction()
        .add(R.id.exercise_fragment_container, fragment)
        .commit()
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_EXERCISE_ID)) {
                throw RuntimeException("Param exercise id is absent")
            }
            exerciseId = intent.getIntExtra(EXTRA_EXERCISE_ID, Exercise.UNDEFINED_ID)
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val EXTRA_EXERCISE_ID = "extra_exercise_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddExercise(context: Context): Intent {
            val intent = Intent(context, ExerciseActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditExercise(context: Context, exerciseId: Int): Intent {
            val intent = Intent(context, ExerciseActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_EXERCISE_ID, exerciseId)
            return intent
        }
    }
}