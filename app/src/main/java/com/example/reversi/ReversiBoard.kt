package com.example.reversi

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import kotlin.math.min


class ReversiBoard(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val scaleFactor = 1.0f
    private var x = 20f
    private var y = 200f

    private var touchCol = 0
    private var touchRow = 0
    private var cellSide = 130f
    private val paint = Paint()
    private var isBlack: Boolean = true
    private var touched = false

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
        drawBoard(canvas)
        defaultPieces(canvas)

        if(touched){
            placePiece(canvas, touchCol, touchRow)
        }

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?: return false

        when (event.action){
            MotionEvent.ACTION_UP -> {
                touched = true
                touchCol = ((event.x - x) / cellSide).toInt()
                touchRow = ((event.y - y) / cellSide).toInt()

                invalidate()
                return true
            }
        }
        return true
    }

    private fun drawBoard(canvas: Canvas) {
        for (row in 0 .. 7)
            for (col in 0 .. 7)
                drawSquareAt(canvas, col, row)
    }

    private fun drawSquareAt(canvas: Canvas, col: Int, row: Int) {
        paint.color = Color.CYAN
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawRect(x + col * cellSide, y + row * cellSide, x + (col + 1)* cellSide, y + (row + 1) * cellSide, paint)
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        canvas.drawRect(x + col * cellSide, y + row * cellSide, x + (col + 1) * cellSide, y + (row + 1) * cellSide, paint)
    }

    private fun defaultPieces(canvas: Canvas) {
        DefaultGame.default()
        for(row in 0..7){
            for (col in 0..7){
                val piece = DefaultGame.pieceAt(col, row)
                if(piece != null){
                    if(piece.colour == PlayerPiece.BLACK){
                        isBlack = true
                        drawPieces(canvas, col, row)
                    }
                    else{
                        isBlack = false
                        drawPieces(canvas, col, row)
                    }
                }
            }
        }
    }

    private fun drawPieces(canvas: Canvas, col: Int, row: Int){
        if(isBlack){
            paint.color = Color.BLACK
            paint.style = Paint.Style.FILL_AND_STROKE
            canvas.drawRect(x + col * cellSide, y + row * cellSide, x + (col + 1) * cellSide, y + (row + 1) * cellSide, paint)
        }
        else{
            paint.color = Color.WHITE
            paint.style = Paint.Style.FILL_AND_STROKE
            canvas.drawRect(x + col * cellSide, y + row * cellSide, x + (col + 1) * cellSide, y + (row + 1) * cellSide, paint)
        }
    }

    private fun placePiece(canvas: Canvas, col: Int, row: Int){
        DefaultGame.newPiece(col, row)
            if(DefaultGame.drawPiece) {
                if(blackTurn) {
                    isBlack = true
                    drawPieces(canvas, col, row)
                    blackTurn = false
                    DefaultGame.drawPiece = false
                }
                else{
                    isBlack = false
                    drawPieces(canvas, col, row)
                    blackTurn = true
                    DefaultGame.drawPiece = false
                }
            }
    }

    companion object {
        var blackTurn: Boolean = true
    }
}
