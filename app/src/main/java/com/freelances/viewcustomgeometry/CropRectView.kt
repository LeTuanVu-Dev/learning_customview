package com.freelances.viewcustomgeometry

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CropRectView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private var rect = RectF(200f, 200f, 600f, 600f)
    private val paint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.STROKE
        strokeWidth = 6f
    }

    private var moving = false
    private var offsetX = 0f
    private var offsetY = 0f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(rect, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (rect.contains(event.x, event.y)) {
                    moving = true
                    offsetX = event.x - rect.left
                    offsetY = event.y - rect.top
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (moving) {
                    val left = event.x - offsetX
                    val top = event.y - offsetY
                    rect.set(left, top, left + rect.width(), top + rect.height())
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP -> moving = false
        }
        return true
    }
}