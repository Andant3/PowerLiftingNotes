package com.example.powerliftingnotes.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.example.powerliftingnotes.R
import com.example.powerliftingnotes.domain.Exercise
import com.google.android.material.textfield.TextInputLayout

class ExerciseActivity : AppCompatActivity() {

//    private lateinit var viewModel: ExerciseViewModel
//
//    private lateinit var tilName: TextInputLayout
//    private lateinit var tilWeight: TextInputLayout
//    private lateinit var tilReps: TextInputLayout
//    private lateinit var etName: EditText
//    private lateinit var etWeight: EditText
//    private lateinit var etReps: EditText
//    private lateinit var btnSave: Button
//
//    private var screenMode = MODE_UNKNOWN
//    private var exerciseId = Exercise.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
//        parseIntent()
//        viewModel = ViewModelProvider(this)[ExerciseViewModel::class.java]
//        initViews()
//        addTextChangeListeners()
//        launchRightMode()
//        observeViewModel()
    }

//    private fun observeViewModel() {
//        viewModel.errorInputName.observe(this) {
//            val message = if (it) {
//                getString(R.string.error_input_name)
//            } else {
//                null
//            }
//            tilName.error = message
//        }
//        viewModel.errorInputWeight.observe(this) {
//            val message = if (it) {
//                getString(R.string.error_input_weight)
//            } else {
//                null
//            }
//            tilWeight.error = message
//        }
//        viewModel.errorInputReps.observe(this) {
//            val message = if (it) {
//                getString(R.string.error_input_reps)
//            } else {
//                null
//            }
//            tilReps.error = message
//        }
//        viewModel.shouldCloseScreen.observe(this) {
//            finish()
//        }
//    }
//
//    private fun launchRightMode() {
//        when (screenMode) {
//            MODE_EDIT -> launchEditMode()
//            MODE_ADD -> launchAddMode()
//        }
//    }
//
//    private fun launchAddMode() {
//        btnSave.setOnClickListener {
//            btnSave.setOnClickListener {
//                viewModel.addExercise(
//                    etName.text?.toString(),
//                    etWeight.text?.toString(),
//                    etReps.text?.toString()
//                )
//            }
//        }
//    }
//
//    private fun launchEditMode() {
//        viewModel.getExercise(exerciseId)
//        viewModel.exercise.observe(this) {
//            etName.setText(it.name)
//            etWeight.setText(it.weight.toString())
//            etReps.setText(it.reps.toString())
//        }
//        btnSave.setOnClickListener {
//            viewModel.editExercise(
//                etName.text?.toString(),
//                etWeight.text?.toString(),
//                etReps.text?.toString()
//            )
//        }
//    }
//
//    private fun parseIntent() {
//        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
//            throw RuntimeException("Param screen mode is absent")
//        }
//        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//        if (mode != MODE_ADD && mode != MODE_EDIT) {
//            throw RuntimeException("Unknown screen mode $mode")
//        }
//        screenMode = mode
//        if (screenMode == MODE_EDIT) {
//            if (!intent.hasExtra(EXTRA_EXERCISE_ID)) {
//                throw RuntimeException("Param exercise id is absent")
//            }
//            exerciseId = intent.getIntExtra(EXTRA_EXERCISE_ID, Exercise.UNDEFINED_ID)
//        }
//    }
//
//    private fun addTextChangeListeners() {
//        etName.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputName()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//        })
//        etWeight.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputWeight()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//        })
//        etReps.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                viewModel.resetErrorInputReps()
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//        })
//    }
//
//    private fun initViews() {
//        tilName = findViewById(R.id.til_name)
//        tilWeight = findViewById(R.id.til_weight)
//        tilReps = findViewById(R.id.til_reps)
//        etName = findViewById(R.id.et_name)
//        etWeight = findViewById(R.id.et_weight)
//        etReps = findViewById(R.id.et_reps)
//        btnSave = findViewById(R.id.btn_save)
//    }

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