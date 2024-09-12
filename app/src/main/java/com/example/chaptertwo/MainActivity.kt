package com.example.chaptertwo

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.example.chaptertwo.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    private lateinit var binding:ActivityMainBinding
    //lateinit' allows initializing a not-null property outside of a constructor

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)

    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // trueButton = findViewById(R.id.true_button)
        // falseButton = findViewById(R.id.false_button)


        binding.trueButton.setOnClickListener {
           /* Toast.makeText(
                this,
                R.string.correct_toast,
                Toast.LENGTH_SHORT
            )
                .show()

            */
            checkAnswer(true)
        }


        binding.falseButton.setOnClickListener {
           /* Toast.makeText(
                this,
                R.string.incorrect_toast,
                Toast.LENGTH_SHORT
            )
                .show()

            */
            checkAnswer(false)


        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
           // val questionTextResId = questionBank[currentIndex].textResId
           // binding.questionTextView.setText(questionTextResId)
            updateQuestion()

        }
        binding.previousButton.setOnClickListener {
            currentIndex = if (currentIndex - 1 < 0) {
                questionBank.size - 1
            } else {
                currentIndex - 1

            }
            updateQuestion()
        }
        // This next bit of code will add an OnClickListener for the TextView
        binding.questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            // val questionTextResId = questionBank[currentIndex].textResId
            // binding.questionTextView.setText(questionTextResId)
            updateQuestion()
        }


      //  val questionTextResId = questionBank [currentIndex].textResId
      //  binding.questionTextView.setText(questionTextResId)
        updateQuestion()
    }
    private fun updateQuestion(){
        val questionTextResId = questionBank [currentIndex].textResId
        binding.questionTextView.setText(questionTextResId)

    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = questionBank[currentIndex].answer

        val messageResID = if (userAnswer == correctAnswer) {
            R.string.correct
        } else{
            R.string.incorrect
        }

        Toast.makeText(this,
            messageResID,
            Toast.LENGTH_SHORT)
            .show()


    }
}