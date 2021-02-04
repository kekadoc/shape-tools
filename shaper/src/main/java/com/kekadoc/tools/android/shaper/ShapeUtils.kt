package com.kekadoc.tools.android.shaper

import androidx.annotation.ColorInt
import android.graphics.drawable.Drawable
import android.graphics.drawable.ColorDrawable
import android.content.res.ColorStateList
import android.graphics.drawable.RippleDrawable
import com.google.android.material.shape.ShapePath

fun ShapePath.startX() = startX
fun ShapePath.startY() = startY
fun ShapePath.endX() = endX
fun ShapePath.endY() = endY

object ShapeUtils {

    /**
     * Ripple color
     * */
    fun getRipple(@ColorInt colorBackground: Int, @ColorInt colorRipple: Int): Drawable {
        val drawable = ColorDrawable(colorBackground)
        return getRipple(colorRipple, drawable)
    }
    /**
     * Create ripple with color and body drawable
     * */
    fun getRipple(@ColorInt rippleColor: Int, backgroundDrawable: Drawable?): Drawable {
        val colorStateList = ColorStateList(arrayOf(intArrayOf()), intArrayOf(rippleColor))
        return RippleDrawable(colorStateList, backgroundDrawable, null)
    }


}