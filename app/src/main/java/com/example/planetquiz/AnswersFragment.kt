package com.example.planetquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class AnswersFragment : Fragment() {

    companion object {

        private const val QUESTION = "question"
        private const val SELECTED_ANSWER = "selected_answer"
        private const val CORRECT_ANSWER = "correct_answer"
        private const val EXPLANATION = "explanation"
        private const val IS_CORRECT = "is_correct"
        private const val QUESTION_NUMBER = "question_number"

        fun newInstance(
            question: String,
            selectedAnswer: String,
            correctAnswer: String,
            explanation: String,
            isCorrect: Boolean,
            questionNumber: Int
        ): AnswersFragment {

            val fragment = AnswersFragment()

            val bundle = Bundle()

            bundle.putString(QUESTION, question)
            bundle.putString(SELECTED_ANSWER, selectedAnswer)
            bundle.putString(CORRECT_ANSWER, correctAnswer)
            bundle.putString(EXPLANATION, explanation)
            bundle.putBoolean(IS_CORRECT, isCorrect)
            bundle.putInt(QUESTION_NUMBER, questionNumber)

            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_answers,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val resultText =
            view.findViewById<TextView>(R.id.resultText)

        val resultSubtitle =
            view.findViewById<TextView>(R.id.resultSubtitle)

        val questionAnswerText =
            view.findViewById<TextView>(R.id.questionAnswerText)

        val explanationText =
            view.findViewById<TextView>(R.id.explanationText)

        val nextButton =
            view.findViewById<Button>(R.id.nextButton)

        val question =
            arguments?.getString(QUESTION) ?: ""

        val selectedAnswer =
            arguments?.getString(SELECTED_ANSWER) ?: ""

        val correctAnswer =
            arguments?.getString(CORRECT_ANSWER) ?: ""

        val explanation =
            arguments?.getString(EXPLANATION) ?: ""

        val isCorrect =
            arguments?.getBoolean(IS_CORRECT) ?: false

        val questionNumber =
            arguments?.getInt(QUESTION_NUMBER) ?: 0

        // Show correct or wrong
        if (isCorrect) {

            resultText.text = "Correct!"
            resultText.setTextColor(
                resources.getColor(R.color.correct_green)
            )

            resultSubtitle.text =
                "Great job! You got it right."

        } else {

            resultText.text = "Wrong!"
            resultText.setTextColor(
                resources.getColor(R.color.wrong_red)
            )

            resultSubtitle.text =
                "Don't worry! Here's the correct answer."
        }

        questionAnswerText.text =
            "$question\n\nYour answer: $selectedAnswer\nCorrect answer: $correctAnswer"

        explanationText.text = explanation

        // Change button text on the last question
        if (questionNumber == 2) {
            nextButton.text = "FINISH"
        } else {
            nextButton.text = "NEXT QUESTION"
        }

        nextButton.setOnClickListener {

            if (questionNumber < 2) {

                // Create the next QuestionsFragment
                val nextFragment =
                    QuestionsFragment()

                // Pass the next question number
                val bundle = Bundle()

                bundle.putInt(
                    "question_number",
                    questionNumber + 1
                )

                nextFragment.arguments = bundle

                // Show the next question
                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        nextFragment
                    )
                    .commit()

            } else {

                // Finish the quiz
                val firstQuestion =
                    QuestionsFragment()

                val bundle = Bundle()

                bundle.putInt(
                    "question_number",
                    0
                )

                firstQuestion.arguments = bundle

                requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        firstQuestion
                    )
                    .commit()
            }
        }
    }
}