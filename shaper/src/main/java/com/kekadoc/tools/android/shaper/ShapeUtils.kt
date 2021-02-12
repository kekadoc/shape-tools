package com.kekadoc.tools.android.shaper

import android.content.Context
import androidx.annotation.ColorInt
import android.graphics.drawable.Drawable
import android.graphics.drawable.ColorDrawable
import android.content.res.ColorStateList
import android.graphics.drawable.RippleDrawable
import com.google.android.material.shape.ShapePath
import com.kekadoc.tools.android.ContextScope

fun ShapePath.startX() = startX
fun ShapePath.startY() = startY
fun ShapePath.endX() = endX
fun ShapePath.endY() = endY

object ShapeUtils {

    @JvmStatic fun builder() = ShapedDrawableBuilder.create()
    @JvmStatic inline fun builder(building: (ShapedDrawableBuilder.() -> Unit)) = ShapedDrawableBuilder.create(building)
    @JvmStatic fun builderWithContext(context: Context) = ShapedDrawableBuilder.createWithContext(context)

    @JvmStatic inline fun createWithContext(context: Context,
                                            building: (ShapedDrawableBuilder.WithContext.() -> Unit))
    = ShapedDrawableBuilder.createWithContext(context, building)

    @JvmStatic fun createWithContext(context: ContextScope) = ShapedDrawableBuilder.createWithContext(context)
    @JvmStatic inline fun createWithContext(context: ContextScope,
                                            building: (ShapedDrawableBuilder.WithContext.() -> Unit))
    = ShapedDrawableBuilder.createWithContext(context, building)

    /**
     * Ripple color
     * */
    @JvmStatic fun getRipple(@ColorInt colorBackground: Int, @ColorInt colorRipple: Int): Drawable {
        val drawable = ColorDrawable(colorBackground)
        return getRipple(colorRipple, drawable)
    }
    /**
     * Create ripple with color and body drawable
     * */
    @JvmStatic fun getRipple(@ColorInt rippleColor: Int, backgroundDrawable: Drawable?): Drawable {
        val colorStateList = ColorStateList(arrayOf(intArrayOf()), intArrayOf(rippleColor))
        return RippleDrawable(colorStateList, backgroundDrawable, null)
    }


}