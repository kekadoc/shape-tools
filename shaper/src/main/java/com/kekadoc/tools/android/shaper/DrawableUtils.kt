package com.kekadoc.tools.android.shaper

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.util.TypedValue
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat


object DrawableUtils {

    @JvmStatic fun getSelectableItemBackground(context: Context): Drawable {
        val outValue = TypedValue()
        context.theme.resolveAttribute(R.attr.selectableItemBackground, outValue, true)
        return ContextCompat.getDrawable(context, outValue.resourceId)!!
    }
    @JvmStatic fun getSelectableItemBackgroundBorderless(context: Context): Drawable {
        val outValue = TypedValue()
        context.theme.resolveAttribute(R.attr.selectableItemBackgroundBorderless, outValue, true)
        return ContextCompat.getDrawable(context, outValue.resourceId)!!
    }

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