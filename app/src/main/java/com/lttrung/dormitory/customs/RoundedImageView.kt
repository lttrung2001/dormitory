package com.lttrung.dormitory.customs

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.lttrung.dormitory.R

class RoundedImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var borderWidth = 0f
    private var borderColor = Color.WHITE
    private var radius = 0f

    init {
        // read the attribute values from the XML
        val a = context.obtainStyledAttributes(attrs, R.styleable.RoundedImageView)
        borderWidth = a.getDimension(R.styleable.RoundedImageView_borderWidth, 0f)
        borderColor = a.getColor(R.styleable.RoundedImageView_borderColor, Color.WHITE)
        radius = a.getDimension(R.styleable.RoundedImageView_radius, 0f)
        a.recycle()
    }

    // override onDraw method
    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        // get the dimensions of the view
        val width = width.toFloat()
        val height = height.toFloat()

        // create a rounded rectangle path
        val path = Path().apply {
            addRoundRect(
                RectF(
                    0f + borderWidth,
                    0f + borderWidth,
                    width - borderWidth,
                    height - borderWidth
                ), radius, radius, Path.Direction.CW
            )
        }

        // create a paint object for the border
        val borderPaint = Paint().apply {
            strokeWidth = borderWidth
            style = Paint.Style.STROKE
            color = borderColor
        }

        // draw the rounded rectangle and border
        canvas.drawPath(path, borderPaint)
        canvas.clipPath(path)

        // call the super method to draw the image inside the rounded rectangle
        super.onDraw(canvas)
    }
}