package com.example.chapterone

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton : Button
    private lateinit var falseButton : Button

    //lateinit allows initializing a not-null property outside of a constructor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)


        trueButton.setOnClickListener {
            Toast.makeText(
                this,
                "Correct",
                Toast.LENGTH_LONG
            )
                .show()

        }
        falseButton.setOnClickListener {
            Toast.makeText(
                this,
                "Incorrect",
                Toast.LENGTH_LONG
            )
                .show()
        }
    }
}