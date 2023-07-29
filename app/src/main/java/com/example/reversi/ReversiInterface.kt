package com.example.reversi

interface ReversiInterface {
    fun pieceAt(col:Int, row: Int) :BoardPieces?
}