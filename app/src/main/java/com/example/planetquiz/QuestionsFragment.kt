package com.example.planetquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.ProgressBar

class QuestionsFragment : Fragment() {

    private val questions = arrayOf(
        "What is the largest planet?",
        "Which planet has the most moons?",
        "Which planet spins on its side?"
    )

    private val answers = arrayOf(
        "JUPITER",
        "SATURN",
        "URANUS"
    )

    private val explanations = arrayOf(
        "Jupiter is the largest planet and is 2.5 times the mass of all the other planets put together.",
        "Saturn has the most moons and has 82 moons.",
        "Uranus spins on its side with its axis at nearly a right angle to the Sun."
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_questions,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionNumber =
            arguments?.getInt("question_number") ?: 0

        val questionText =
            view.findViewById<TextView>(R.id.questionText)

        val progressText =
            view.findViewById<TextView>(R.id.progressText)

        val progressBar =
            view.findViewById<ProgressBar>(R.id.progressBar)

        progressText.text =
            "QUESTION ${questionNumber + 1} OF 3"

        progressBar.progress =
            questionNumber + 1

        questionText.text = questions[questionNumber]

        val buttons = listOf(
            view.findViewById<Button>(R.id.mercuryButton),
            view.findViewById<Button>(R.id.venusButton),
            view.findViewById<Button>(R.id.earthButton),
            view.findViewById<Button>(R.id.marsButton),
            view.findViewById<Button>(R.id.jupiterButton),
            view.findViewById<Button>(R.id.saturnButton),
            view.findViewById<Button>(R.id.uranusButton),
            view.findViewById<Button>(R.id.neptuneButton)
        )

        for (button in buttons) {

            button.setOnClickListener {

                val selectedAnswer =
                    button.text.toString()

                val correctAnswer =
                    answers[questionNumber]

                val isCorrect =
                    selectedAnswer == correctAnswer

                val answerFragment =
                    AnswersFragment.newInstance(
                        questions[questionNumber],
                        selectedAnswer,
                        correctAnswer,
                        explanations[questionNumber],
                        isCorrect,
                        questionNumber
                    )

                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        answerFragment
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}