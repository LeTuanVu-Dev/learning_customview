package com.freelances.viewcustomgeometry

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.util.Calendar
import kotlin.math.cos
import kotlin.math.sin

class AnalogClockView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 8f
        paint.color = Color.BLACK
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val cx = width / 2f
        val cy = height / 2f
        val radius = width.coerceAtMost(height) / 2f - 16

        canvas.drawCircle(cx, cy, radius, paint)

        val calendar = Calendar.getInstance()
        val second = calendar.get(Calendar.SECOND)
        val angle = Math.toRadians((second * 6 - 90).toDouble())
        val x = cx + radius * 0.8 * cos(angle)
        val y = cy + radius * 0.8 * sin(angle)
        canvas.drawLine(cx, cy, x.toFloat(), y.toFloat(), paint)

        postInvalidateDelayed(1000)
    }
}