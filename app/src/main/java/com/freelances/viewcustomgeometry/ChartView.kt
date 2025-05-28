package com.freelances.viewcustomgeometry

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ChartView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    var values = listOf(10, 20, 35, 50, 25)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.MAGENTA
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val barWidth = width / (values.size * 2f)
        values.forEachIndexed { i, value ->
            val left = i * barWidth * 2
            val top = height - value * 10f
            canvas.drawRect(left, top, left + barWidth, height.toFloat(), paint)
        }
    }
}