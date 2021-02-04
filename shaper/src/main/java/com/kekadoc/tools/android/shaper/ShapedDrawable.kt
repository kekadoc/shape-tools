package com.kekadoc.tools.android.shaper

import android.graphics.drawable.Drawable
import androidx.appcompat.graphics.drawable.DrawableWrapper
import com.google.android.material.shape.MaterialShapeDrawable

class ShapedDrawable internal constructor(drawable: Drawable,
                                          private val shapeDrawable: MaterialShapeDrawable) : DrawableWrapper(drawable) {
    fun setInterpolation(interpolation: Float) {
        shapeDrawable.interpolation = interpolation
    }
}