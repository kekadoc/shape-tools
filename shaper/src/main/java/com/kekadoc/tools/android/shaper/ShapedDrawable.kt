package com.kekadoc.tools.android.shaper

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RippleDrawable
import androidx.annotation.*
import androidx.annotation.IntRange
import androidx.appcompat.graphics.drawable.DrawableWrapper
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.kekadoc.tools.android.ContextScope
import com.kekadoc.tools.android.shaper.edges.DefEdgeTreatment
import com.kekadoc.tools.builder.AbstractBuilder
import java.lang.IndexOutOfBoundsException

inline fun shapedDrawable(building: ShapedDrawableBuilder.() -> Unit): ShapedDrawable {
    return ShapedDrawableBuilder.create(building).build()
}
inline fun shapedDrawable(context: Context, building: ShapedDrawableBuilder.WithContext.() -> Unit): ShapedDrawable {
    return ShapedDrawableBuilder.createWithContext(context, building).build()
}
inline fun shapedDrawable(context: ContextScope, building: ShapedDrawableBuilder.WithContext.() -> Unit): ShapedDrawable {
    return ShapedDrawableBuilder.createWithContext(context, building).build()
}

@SuppressLint("RestrictedApi")
class ShapedDrawable internal constructor(drawable: Drawable) : DrawableWrapper(drawable) {

    internal var materialShapeDrawable: MaterialShapeDrawable? = null
    internal var rippleDrawable: RippleDrawable? = null

    fun getShapeDrawable(): MaterialShapeDrawable? = materialShapeDrawable
    fun getRippleDrawable(): RippleDrawable? = rippleDrawable

}

open class ShapedDrawableBuilder : AbstractBuilder<ShapedDrawable, ShapeAppearanceModel>() {

    companion object {

        @JvmStatic fun create() = ShapedDrawableBuilder()
        @JvmStatic inline fun create(building: (ShapedDrawableBuilder.() -> Unit)): ShapedDrawableBuilder {
            val builder = ShapedDrawableBuilder()
            building.invoke(builder)
            return builder
        }
        @JvmStatic fun createWithContext(context: Context) = WithContext(context)
        @JvmStatic inline fun createWithContext(context: Context, building: (WithContext.() -> Unit)): WithContext {
            val builder = WithContext(context)
            building.invoke(builder)
            return builder
        }
        @JvmStatic fun createWithContext(context: ContextScope) = WithContext(context)
        @JvmStatic inline fun createWithContext(context: ContextScope, building: (WithContext.() -> Unit)): WithContext {
            val builder = WithContext(context)
            building.invoke(builder)
            return builder
        }

    }

    private var rippleShapeDrawable: MaterialShapeDrawable? = null
    private val materialShapeDrawable = object : MaterialShapeDrawable() {
        override fun setShapeAppearanceModel(shapeAppearanceModel: ShapeAppearanceModel) {
            super.setShapeAppearanceModel(shapeAppearanceModel)
            rippleShapeDrawable?.shapeAppearanceModel = shapeAppearanceModel
        }
        override fun setInterpolation(interpolation: Float) {
            super.setInterpolation(interpolation)
            rippleShapeDrawable?.interpolation = interpolation
        }
    }.apply {
        shadowCompatibilityMode = MaterialShapeDrawable.SHADOW_COMPAT_MODE_ALWAYS
    }
    private val modelBuilder: ShapeAppearanceModel.Builder = ShapeAppearanceModel.Builder().apply {
        setAllEdges(DefEdgeTreatment())
    }

    private var rippleColor: Int? = null

    @IntRange(from = 0, to = 255)
    private var alpha = 255

    @Px
    private var inset: IntArray? = null
    @Px
    private var padding: IntArray? = null


    fun setInset(@Px left: Int = 0, @Px top: Int = 0, @Px right: Int = 0, @Px bottom: Int = 0) = apply {
        if (inset == null) inset = intArrayOf(left, top, right, bottom)
        else {
            inset!![0] = left
            inset!![1] = top
            inset!![2] = right
            inset!![3] = bottom
        }
    }
    fun setInset(inset: Int) = apply {
        setInset(inset, inset, inset, inset)
    }
    fun setInset(@Size(value = 4) insetArray: IntArray?) = apply {
        if (insetArray != null && insetArray.size != 4) throw IndexOutOfBoundsException()
        inset = insetArray
    }


    fun setPadding(@Px left: Int = 0, @Px top: Int = 0, @Px right: Int = 0, @Px bottom: Int = 0) = apply {
        if (padding == null) padding = intArrayOf(left, top, right, bottom)
        else {
            padding!![0] = left
            padding!![1] = top
            padding!![2] = right
            padding!![3] = bottom
        }
    }
    fun setPadding(@Px padding: Int) = apply { setPadding(padding, padding, padding, padding) }
    fun setPadding(@Px paddingArray: IntArray?) = apply {
        if (paddingArray != null && paddingArray.size != 4) throw IndexOutOfBoundsException()
        padding = paddingArray
    }


    fun setTint(@ColorInt color: Int) = apply { materialShapeDrawable.setTint(color) }
    fun setShadowColor(@ColorInt color: Int) = apply { materialShapeDrawable.setShadowColor(color) }
    fun setRippleColor(@ColorInt color: Int) = apply { rippleColor = color }
    fun setElevation(@Px elevation: Float) = apply { materialShapeDrawable.elevation = elevation }
    fun setAlpha(@IntRange(from = 0, to = 255) alpha: Int) = apply { this.alpha = alpha }
    fun setAlpha(@FloatRange(from = 0.0, to = 1.0) alpha: Float) = apply { this.alpha =
        (255 * alpha).toInt()
    }
    fun setStroke(@Px width: Float, @ColorInt color: Int) = apply { materialShapeDrawable.setStroke(width, color) }

    fun shape(shapeAppearanceBuilder: ShapeAppearanceModel.Builder.() -> Unit) {
        shapeAppearanceBuilder.invoke(modelBuilder)
    }

    override fun onCreateParams(): ShapeAppearanceModel {
        return modelBuilder.build()
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateResult(param: ShapeAppearanceModel): ShapedDrawable {
        this.materialShapeDrawable.shapeAppearanceModel = param
        padding?.let {
            this.materialShapeDrawable.setPadding(it[0], it[1], it[2], it[3])
        }


        var resultDrawable: Drawable = this.materialShapeDrawable
        var rippleDrawable: RippleDrawable? = null
        //return InsetDrawable(drawable, R.attr.insetLeft, R.attr.insetTop, R.attr.insetRight, R.attr.insetBottom)
        rippleColor?.let {
            rippleShapeDrawable = MaterialShapeDrawable(param).apply {
                interpolation = materialShapeDrawable.interpolation
            }
            val colorStateList = ColorStateList(arrayOf(intArrayOf()), intArrayOf(it))
            rippleDrawable = RippleDrawable(colorStateList, null, rippleShapeDrawable)
            resultDrawable = LayerDrawable(arrayOf(resultDrawable, rippleDrawable)).apply {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
                    setPadding(leftPadding, topPadding, rightPadding, bottomPadding)
            }
        }
        inset?.let {
            resultDrawable = InsetDrawable(resultDrawable, it[0], it[1], it[2], it[3])
        }

        return ShapedDrawable(resultDrawable).apply {
            this.alpha = this@ShapedDrawableBuilder.alpha
            this.materialShapeDrawable = this@ShapedDrawableBuilder.materialShapeDrawable
            this.rippleDrawable = rippleDrawable
        }
    }

    class WithContext constructor(private val context: Context) : ShapedDrawableBuilder(), ContextScope {
        constructor(context: ContextScope): this(context.getContext())
        override fun getContext(): Context {
            return context
        }
    }

}