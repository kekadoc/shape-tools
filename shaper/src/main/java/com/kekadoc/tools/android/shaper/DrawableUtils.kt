package com.kekadoc.tools.android.shaper

import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import androidx.annotation.ColorInt

object DrawableUtils {

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