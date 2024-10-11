package com.example.chapterseven

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"


class QuizViewModel(private val savedStateHandle: SavedStateHandle):ViewModel() {


    /* init {

        Log.d(TAG, "ViewModel instance created")
    }

    override fun onCleared() {

        Log.d(TAG, "ViewModel instance about to be destroyed")
    }
    */
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)

    )
    private val cheatStatusKey = "CHEAT_STATUS_KEY"
    private var cheatStatus: BooleanArray
        get() = savedStateHandle.get(cheatStatusKey) ?: BooleanArray(questionBank.size)
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)


    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    private var currentIndex
        get() =  savedStateHandle.get(CURRENT_INDEX_KEY)?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer:Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    // Track if the user cheated on current question
    fun hasCheatedOnCurrentQuestion(): Boolean {
        return cheatStatus[currentIndex]
    }

    // Mark the current question as cheated
    fun markQuestionAsCheated() {
        cheatStatus[currentIndex] = true
        savedStateHandle.set(cheatStatusKey, cheatStatus) // Save updated status
    }

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size

    }

    companion object {
        private const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
        private const val  IS_CHEAT_KEY = "IS_CHEATER_KEY"
    }

}