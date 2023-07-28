package com.example.reversi

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var default = DefaultGame
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resetButton = findViewById<Button>(R.id.reset_button)

        resetButton.setOnClickListener{
            DefaultGame.reset()
        }
    }
}
