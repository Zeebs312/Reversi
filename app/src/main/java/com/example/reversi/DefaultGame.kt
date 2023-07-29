package com.example.reversi

import android.R
import android.util.Log

import android.widget.TextView

object DefaultGame {
    var pieces = mutableSetOf<BoardPieces>()
    var drawPiece:Boolean = false

    init{
        reset()
    }

     fun pieceAt(col: Int, row: Int): BoardPieces? {
        for (piece in pieces){
            if(col == piece.col && row == piece.row){
                return piece
            }
        }
        return null
    }

    fun addPiece(piece: BoardPieces) {
        pieces.add(piece)
    }

    fun default(){
        addPiece(BoardPieces(3, 3, Player.BLACK, PlayerPiece.BLACK))
        addPiece(BoardPieces(4, 3, Player.WHITE, PlayerPiece.WHITE))
        addPiece(BoardPieces(4, 4, Player.BLACK, PlayerPiece.BLACK))
        addPiece(BoardPieces(3, 4, Player.WHITE, PlayerPiece.WHITE))
    }

    fun reset(){
        pieces.clear()
        ReversiBoard.blackTurn = true
        addPiece(BoardPieces(3, 3, Player.BLACK, PlayerPiece.BLACK))
        addPiece(BoardPieces(4, 3, Player.WHITE, PlayerPiece.WHITE))
        addPiece(BoardPieces(4, 4, Player.BLACK, PlayerPiece.BLACK))
        addPiece(BoardPieces(3, 4, Player.WHITE, PlayerPiece.WHITE))
    }

    fun newPiece(col: Int, row: Int){
                val piece = pieceAt(col, row)
                if(piece == null){
                    if(ReversiBoard.blackTurn){
                        addPiece(BoardPieces(col, row, Player.BLACK, PlayerPiece.BLACK))
                        drawPiece = true
                    }
                else{
                        addPiece(BoardPieces(col, row, Player.WHITE, PlayerPiece.WHITE))
                        drawPiece = true
            }
        }
        Log.d("DefaultGame", toString())
    }

    override fun toString(): String {
        var desc = " \n"
        for (row in 7 downTo 0) {
            desc += "$row"
            desc += boardRow(row)
            desc += "\n"
        }
        desc += "  0 1 2 3 4 5 6 7"

        return desc
    }

    private fun boardRow(row: Int) : String {
        var desc = ""
        for (col in 0 until 8) {
            desc += " "
            desc += pieceAt(col, row)?.let {
                val white = it.player == Player.WHITE
                when (it.colour) {
                    PlayerPiece.WHITE -> if (white) "w" else "W"
                    PlayerPiece.BLACK -> if (white) "b" else "B"
                }
            } ?: "."
        }
        return desc
    }
}