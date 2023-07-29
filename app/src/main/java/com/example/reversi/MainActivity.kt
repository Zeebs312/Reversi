package com.example.reversi

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.os.IResultReceiver.Default
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity(), ReversiInterface {
    var default = DefaultGame
    private lateinit var resetButton: Button
    private lateinit var board: ReversiBoard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = findViewById<ReversiBoard>(R.id.reversi_board)
        resetButton = findViewById<Button>(R.id.reset_button)

        resetButton.setOnClickListener{
            DefaultGame.reset()
            board.invalidate()
        }

        currentTurn()
    }

    override fun pieceAt(col: Int, row: Int): BoardPieces? {
        return DefaultGame.pieceAt(col, row)
    }

    private fun currentTurn(){
        var tv: TextView = findViewById(R.id.player_turn)
        if(ReversiBoard.blackTurn){
            tv.text = "Black's Turn"
        }
        else{
            tv.text = "White's Turn"
        }
    }
}
