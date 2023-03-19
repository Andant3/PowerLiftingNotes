package com.example.powerliftingnotes.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.powerliftingnotes.R
import com.example.powerliftingnotes.domain.Exercise
import com.google.android.material.textfield.TextInputLayout

class ExerciseFragment(
) : Fragment() {

    private var screenMode: String = MODE_UNKNOWN
    private var exerciseId: Int = Exercise.UNDEFINED_ID

    private lateinit var viewModel: ExerciseViewModel

    private lateinit var tilName: TextInputLayout
    private lateinit var tilWeight: TextInputLayout
    private lateinit var tilReps: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var etWeight: EditText
    private lateinit var etReps: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ExerciseViewModel::class.java]
        initViews(view)
        addTextChangeListeners()
        launchRightMode()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.errorInputName.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            tilName.error = message
        }
        viewModel.errorInputWeight.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_weight)
            } else {
                null
            }
            tilWeight.error = message
        }
        viewModel.errorInputReps.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_reps)
            } else {
                null
            }
            tilReps.error = message
        }
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            activity?.onBackPressedDispatcher?.onBackPressed()
            requireActivity().onBackPressedDispatcher.onBackPressed()         }
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun launchAddMode() {
        btnSave.setOnClickListener {
            btnSave.setOnClickListener {
                viewModel.addExercise(
                    etName.text?.toString(),
                    etWeight.text?.toString(),
                    etReps.text?.toString()
                )
            }
        }
    }

    private fun launchEditMode() {
        viewModel.getExercise(exerciseId)
        viewModel.exercise.observe(viewLifecycleOwner) {
            etName.setText(it.name)
            etWeight.setText(it.weight.toString())
            etReps.setText(it.reps.toString())
        }
        btnSave.setOnClickListener {
            viewModel.editExercise(
                etName.text?.toString(),
                etWeight.text?.toString(),
                etReps.text?.toString()
            )
        }
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_ADD && mode != MODE_EDIT) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!args.containsKey(EXERCISE_ID)) {
                throw RuntimeException("Param exercise id is absent")
            }
            exerciseId = args.getInt(EXERCISE_ID, Exercise.UNDEFINED_ID)
        }
    }

    private fun addTextChangeListeners() {
        etName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputName()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        etWeight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputWeight()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
        etReps.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputReps()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun initViews(view: View) {
        tilName = view.findViewById(R.id.til_name)
        tilWeight = view.findViewById(R.id.til_weight)
        tilReps = view.findViewById(R.id.til_reps)
        etName = view.findViewById(R.id.et_name)
        etWeight = view.findViewById(R.id.et_weight)
        etReps = view.findViewById(R.id.et_reps)
        btnSave = view.findViewById(R.id.btn_save)
    }

    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val EXERCISE_ID = "extra_exercise_id"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val MODE_UNKNOWN = ""

        fun newInstanceAddExercise(): ExerciseFragment{
            return ExerciseFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditExercise(exerciseId: Int): ExerciseFragment{
            return ExerciseFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(EXERCISE_ID, exerciseId)
                }
            }
        }
    }
}