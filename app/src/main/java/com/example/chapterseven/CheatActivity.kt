package com.example.chapterseven

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chapterseven.databinding.ActivityCheatBinding

private const val EXTRA_ANSWER_IS_TRUE = "com.bateman.bignerdranch.android.geoquiz.answer_is_true"
const val EXTRA_ANSWER_SHOWN = "com.bateman.bigdranch.android.geoquiz.answer_shown"
class CheatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheatBinding
    private var answerIsTrue = false
    private var answerShown = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_cheat)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        if(savedInstanceState != null) {
            answerShown = savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN, false)
            if (answerShown){
                showAnswer()
            }
        }

        binding.showAnswerButton.setOnClickListener {
            showAnswer()
            answerShown = true
            setAnswerShownResult(true) // Inform MainActivity that the answer was shown

        }


    }
    override fun onSaveInstanceState(outState:Bundle){
        super.onSaveInstanceState(outState)
        outState.putBoolean(EXTRA_ANSWER_SHOWN, answerShown)
    }
    private fun showAnswer(){
        val answerText = when {
            answerIsTrue -> R.string.correct_toast
            else -> R.string.incorrect_toast
        }
        binding.answerTextView.setText(answerText)

        setAnswerShownResult(true)
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {

            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}
