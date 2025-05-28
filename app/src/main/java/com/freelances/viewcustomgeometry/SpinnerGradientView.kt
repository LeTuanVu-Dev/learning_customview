package com.freelances.viewcustomgeometry

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SpinnerGradientView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var angle = 0f
    private val animator = ValueAnimator.ofFloat(0f, 360f)

    init {
        animator.duration = 2000
        animator.repeatCount = ValueAnimator.INFINITE
        animator.addUpdateListener {
            angle = it.animatedValue as Float
            invalidate()
        }
        animator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val cx = width / 2f
        val cy = height / 2f
        val radius = width.coerceAtMost(height) / 2f - 8
        val shader = android.graphics.SweepGradient(cx, cy, Color.RED, Color.BLUE)

        paint.shader = shader
        canvas.save()
        canvas.rotate(angle, cx, cy)
        canvas.drawCircle(cx, cy, radius, paint)
        canvas.restore()
    }
}