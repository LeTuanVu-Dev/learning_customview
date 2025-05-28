package com.freelances.viewcustomgeometry

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class GeometricView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var colorView: Int = 0
    private var nameView: String = "circle"
    private var widthMeasureSpec: Int = 100
    private var heightMeasureSpec: Int = 100
    private var triggerPath = Path()
    private var paint = Paint()
    private var lastX = 0f
    private var lastY = 0f

    private fun setUpViewAttrs(attrs: AttributeSet) {
        val type = context.theme.obtainStyledAttributes(attrs, R.styleable.GeometricView, 0, 0)
        colorView = type.getColor(R.styleable.GeometricView_colorView, Color.RED)
        nameView = type.getString(R.styleable.GeometricView_name) ?: "circle"
        paint.color = colorView
        type.recycle()
    }

    init {
        setUpPaint()
        setUpViewAttrs(attrs)
    }

    fun setName(name:String){
        nameView = name
        invalidate()
        requestLayout()
    }

    fun setColor(color:Int){
        colorView = color
        invalidate()
        requestLayout()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        this.widthMeasureSpec = widthMeasureSpec
        this.heightMeasureSpec = heightMeasureSpec
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // onDraw
        Log.d("GeometricView", "onDraw: nameView = $nameView, size = $width x $height")

        if (nameView == "square"){
            canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        }
        if (nameView == "circle"){
            val radius = minOf(width, height) / 2f
            canvas.drawCircle(width / 2f, height / 2f, radius, paint)
        }
        if (nameView == "tamgiac"){
            canvas.drawPath(drawTamGiac(), paint)
        }

    }

    // drawTamGiac
    private fun drawTamGiac(): Path {
        triggerPath.reset() // phải reset để tránh vẽ chồng lặp path cũ
        triggerPath.moveTo(0f, height.toFloat())
        triggerPath.lineTo(width.toFloat(), height.toFloat())
        triggerPath.lineTo(width / 2f, 0f)
        triggerPath.close()
        return triggerPath
    }

    private fun setUpPaint(){
        paint = Paint().apply {
            style = Paint.Style.FILL
            color = colorView
            textSize = 30f
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.rawX
                lastY = event.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = event.rawX - lastX
                val dy = event.rawY - lastY

                // Di chuyển view bằng cách cập nhật vị trí mới
                x += dx
                y += dy

                // Cập nhật vị trí hiện tại cho lần tiếp theo
                lastX = event.rawX
                lastY = event.rawY
            }
        }
        return true
    }

}