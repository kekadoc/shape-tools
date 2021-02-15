package com.kekadoc.tools.android.shaper

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.BlendMode
import android.graphics.PorterDuff
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RippleDrawable
import android.os.Build
import androidx.annotation.*
import androidx.annotation.IntRange
import androidx.appcompat.graphics.drawable.DrawableWrapper
import androidx.core.graphics.plus
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.kekadoc.tools.android.ContextScope
import com.kekadoc.tools.android.shaper.edges.DefEdgeTreatment
import com.kekadoc.tools.annotations.FractionValue
import com.kekadoc.tools.builder.AbstractBuilder
import java.lang.IndexOutOfBoundsException
import kotlin.math.roundToInt

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

fun ShapedDrawableBuilder.tintOf(@ColorInt color: Int) {
    tintList = ColorStateList.valueOf(color)
}

@ShapeBuildingContext
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

    private var alpha: Alpha? = null
    private var inset: Inset? = null
    private var insetFraction: InsetFraction? = null
    private var padding: Padding? = null
    private var ripple: Ripple? = null
    private var tint: Tint? = null
    private var stroke: Stroke? = null
    private var shadow: Shadow? = null

    var tintList: ColorStateList? = null

    fun ripple(builder: Ripple.() -> Unit): Ripple {
        ripple = Ripple()
        builder.invoke(ripple!!)
        return ripple!!
    }
    fun alpha(builder: Alpha.() -> Unit): Alpha {
        alpha = Alpha()
        builder.invoke(alpha!!)
        return alpha!!
    }
    fun padding(builder: Padding.() -> Unit): Padding {
        padding = Padding()
        builder.invoke(padding!!)
        return padding!!
    }
    fun inset(builder: Inset.() -> Unit): Inset {
        val inset = Inset()
        builder.invoke(inset)
        this.inset = inset
        return inset
    }
    fun insetFraction(builder: InsetFraction.() -> Unit): InsetFraction {
        val inset = InsetFraction()
        builder.invoke(inset)
        this.insetFraction = inset
        return inset
    }
    fun tint(builder: Tint.() -> Unit): Tint {
        val inset = Tint()
        builder.invoke(inset)
        this.tint = inset
        return inset
    }
    fun stroke(builder: Stroke.() -> Unit): Stroke {
        val inset = Stroke()
        builder.invoke(inset)
        this.stroke = inset
        return inset
    }
    fun shadow(builder: Shadow.() -> Unit): Shadow {
        val inset = Shadow()
        builder.invoke(inset)
        this.shadow = inset
        return inset
    }

    fun shape(@ShapeBuildingContext shapeAppearanceBuilder: ((ShapeAppearanceModel.Builder).() -> Unit)) {
        shapeAppearanceBuilder.invoke(modelBuilder)
    }



    override fun onCreateParams(): ShapeAppearanceModel {
        return modelBuilder.build()
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateResult(param: ShapeAppearanceModel): ShapedDrawable {
        this.materialShapeDrawable.shapeAppearanceModel = param
        padding?.let {
            this.materialShapeDrawable.setPadding(it.left, it.top, it.right, it.bottom)
        }

        var resultDrawable: Drawable = this.materialShapeDrawable
        var rippleDrawable: RippleDrawable? = null
        if (ripple != null) ripple!!.color?.let {

            rippleShapeDrawable = MaterialShapeDrawable(param).apply {
                interpolation = materialShapeDrawable.interpolation
            }
            rippleDrawable = RippleDrawable(it, null, rippleShapeDrawable)
            resultDrawable = LayerDrawable(arrayOf(resultDrawable, rippleDrawable))
        }
        inset?.let {
            resultDrawable = InsetDrawable(resultDrawable, it.left, it.top, it.right, it.bottom)
        }

        return ShapedDrawable(resultDrawable).apply {
            this@ShapedDrawableBuilder.alpha?.let {
                this.alpha = it.value
            }
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

    inner class Shadow : DrawableBuilder {

        var radius: Float = 0f
            get() = getDrawable().elevation
            set(value) {
                field = value
                getDrawable().elevation = value
            }

        var mode: Int = getDrawable().shadowCompatibilityMode
            get() = getDrawable().shadowCompatibilityMode
            set(value) {
                field = value
                getDrawable().shadowCompatibilityMode = value
            }

        private fun getDrawable() = this@ShapedDrawableBuilder.materialShapeDrawable

    }
    inner class Stroke : DrawableBuilder {

        var width: Float = 0f
            get() = getDrawable().strokeWidth
            set(value) {
                field = value
                getDrawable().strokeWidth = value
            }
        var color: ColorStateList? = null
            get() = getDrawable().strokeColor
            set(value) {
                field = value
                getDrawable().strokeColor = value
            }

        fun colorOf(@ColorInt color: Int) {
            this.color = ColorStateList.valueOf(color)
        }

        private fun getDrawable() = this@ShapedDrawableBuilder.materialShapeDrawable

    }
    data class Alpha(@IntRange(from = 0, to = 255) var value: Int = 255): DrawableBuilder {
        fun fraction(@FractionValue fraction: Float) {
            value = (fraction * 255).toInt()
        }
    }
    data class Padding(@Px var left: Int = 0,
                     @Px var top: Int = 0,
                     @Px var right: Int = 0,
                     @Px var bottom: Int = 0): DrawableBuilder {
        fun all(@Px inset: Int) {
            left = inset
            top = inset
            right = inset
            bottom = inset
        }
    }
    data class Inset(@Px var left: Int = 0,
                     @Px var top: Int = 0,
                     @Px var right: Int = 0,
                     @Px var bottom: Int = 0): DrawableBuilder {
        fun all(@Px inset: Int) {
            left = inset
            top = inset
            right = inset
            bottom = inset
        }
    }
    data class Ripple(var color: ColorStateList? = null): DrawableBuilder {
        //ColorStateList(arrayOf(intArrayOf()), intArrayOf(color))
        fun colorOf(@ColorInt color: Int) {
            this.color = ColorStateList.valueOf(color)
        }
    }
    inner class Tint : DrawableBuilder {

        var tintList: ColorStateList? = null
            get() = getDrawable().tintList
            set(value) {
                field = value
                getDrawable().tintList = value
            }
        var tintMode: PorterDuff.Mode? = null
            set(value) {
                field = value
                getDrawable().setTintMode(value)
            }
        var blendMode: BlendMode? = null
            @RequiresApi(Build.VERSION_CODES.Q)
            set(value) {
                field = value
                getDrawable().setTintBlendMode(value)
            }

        fun tintOf(@ColorInt color: Int) {
            getDrawable().setTint(color)
        }

        private fun getDrawable() = this@ShapedDrawableBuilder.materialShapeDrawable

    }


    // TODO: 15.02.2021 Update android-tools and use annotation
    data class InsetFraction(@FractionValue var left: Float = 0f,
                             @FractionValue var top: Float = 0f,
                             @FractionValue var right: Float = 0f,
                             @FractionValue var bottom: Float = 0f): DrawableBuilder {
        fun all(@FractionValue inset: Float) {
            left = inset
            top = inset
            right = inset
            bottom = inset
        }
    }

    @ShapeBuildingContext
    internal interface DrawableBuilder

}

@DslMarker
annotation class ShapeBuildingContext