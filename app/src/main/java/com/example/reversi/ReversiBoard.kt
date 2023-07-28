package com.example.reversi

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.min


class ReversiBoard(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val scaleFactor = 1.0f
    private var x = 20f
    private var y = 200f
    private var cellSide = 130f
    private val paint = Paint()
    private var isBlack: Boolean = true


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val smaller = min(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(smaller, smaller)
    }

    // overridden draw function that will draw the canvas depending on the mode selected
    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        val chessBoardSide = min(width, height) * scaleFactor
        cellSide = chessBoardSide / 8f
        x = (width - chessBoardSide) / 2f
        y = (height - chessBoardSide) / 2f
        drawboard(canvas)
        defaultPieces(canvas)
    }

    private fun drawboard(canvas: Canvas) {
        for (row in 0 .. 7)
            for (col in 0 .. 7)
                drawSquareAt(canvas, col, row)
    }

    private fun drawSquareAt(canvas: Canvas, col: Int, row: Int) {
        paint.color = Color.CYAN
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas?.drawRect(x + col * cellSide, y + row * cellSide, x + (col + 1)* cellSide, y + (row + 1) * cellSide, paint)
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        canvas?.drawRect(x + col * cellSide, y + row * cellSide, x + (col + 1) * cellSide, y + (row + 1) * cellSide, paint)
    }

    private fun defaultPieces(canvas: Canvas) {
        DefaultGame.reset()
        for(row in 0..7){
            for (col in 0..7){
                val piece = DefaultGame.pieceAt(col, row)
                if(piece != null){
                    if(piece.colour == PlayerPiece.BLACK){
                        isBlack = true
                        drawPieceAt(canvas, col, row)
                    }
                    else{
                        isBlack = false
                        drawPieceAt(canvas, col, row)
                    }
                }
            }
        }
    }

    private fun drawPieceAt(canvas: Canvas, col: Int, row: Int){
        if(isBlack){
            paint.color = Color.BLACK
            paint.style = Paint.Style.FILL_AND_STROKE
            canvas?.drawRect(x + col * cellSide, y + row * cellSide, x + (col + 1) * cellSide, y + (row + 1) * cellSide, paint)
        }
        else{
            paint.color = Color.WHITE
            paint.style = Paint.Style.FILL_AND_STROKE
            canvas?.drawRect(x + col * cellSide, y + row * cellSide, x + (col + 1) * cellSide, y + (row + 1) * cellSide, paint)
        }
    }
}