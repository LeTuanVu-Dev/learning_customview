package com.freelances.viewcustomgeometry

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.graphics.Path
import android.view.MotionEvent

class SignaturePadView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    private val paths = mutableListOf<Path>()        // Các nét đã vẽ
    private val undonePaths = mutableListOf<Path>()  // Các nét đã undo
    private var currentPath: Path? = null            // Nét đang vẽ (chưa xong)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Vẽ các nét hoàn chỉnh
        for (p in paths) canvas.drawPath(p, paint)
        // Vẽ nét đang vẽ (chưa được add)
        currentPath?.let { canvas.drawPath(it, paint) }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath = Path().apply {
                    moveTo(event.x, event.y)
                }
                // Khi bắt đầu vẽ mới, xoá redo stack
                undonePaths.clear()
            }
            MotionEvent.ACTION_MOVE -> {
                currentPath?.lineTo(event.x, event.y)
            }
            MotionEvent.ACTION_UP -> {
                currentPath?.let {
                    paths.add(it)      // Chỉ add khi đã hoàn thành nét
                }
                currentPath = null     // Reset lại nét tạm
            }
        }
        invalidate()
        return true
    }

    fun undo() {
        if (paths.isNotEmpty()) {
            val last = paths.removeAt(paths.lastIndex)
            undonePaths.add(last)
            invalidate()
        }
    }

    fun redo() {
        if (undonePaths.isNotEmpty()) {
            val last = undonePaths.removeAt(undonePaths.lastIndex)
            paths.add(last)
            invalidate()
        }
    }

    fun clear() {
        paths.clear()
        undonePaths.clear()
        currentPath = null
        invalidate()
    }
}

