package com.example.reversi

object DefaultGame {
    var pieces = mutableSetOf<BoardPieces>()

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

    fun reset(){
        pieces.clear()
        pieces.add(BoardPieces(3, 3, Player.BLACK, PlayerPiece.BLACK))
        pieces.add(BoardPieces(4, 3, Player.WHITE, PlayerPiece.WHITE))
        pieces.add(BoardPieces(4, 4, Player.BLACK, PlayerPiece.BLACK))
        pieces.add(BoardPieces(3, 4, Player.WHITE, PlayerPiece.WHITE))
    }
}