package com.kekadoc.tools.android.shaper

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RippleDrawable
import androidx.annotation.*
import androidx.annotation.IntRange
import com.google.android.material.shape.*
import com.kekadoc.tools.android.ContextScope
import com.kekadoc.tools.android.shaper.corners.CornerType
import com.kekadoc.tools.android.shaper.edges.DefEdgeTreatment
import com.kekadoc.tools.builder.AbstractBuilder

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

        @JvmStatic fun ShapedDrawableBuilder.roundAllCorners(@Px radius: Float) = apply {
            setAllCorner(RoundedCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.roundTopLeftCorner(@Px radius: Float) = apply {
            setTopLeftCorner(RoundedCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.roundTopRightCorner(@Px radius: Float) = apply {
            setTopRightCorner(RoundedCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.roundBottomLeftCorner(@Px radius: Float) = apply {
            setBottomLeftCorner(RoundedCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.roundBottomRightCorner(@Px radius: Float) = apply {
            setBottomRightCorner(RoundedCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.roundedAllCorners(radius: CornerSize) = apply {
            setAllCorner(RoundedCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.roundTopLeftCorner(radius: CornerSize) = apply {
            setTopLeftCorner(RoundedCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.roundTopRightCorner(radius: CornerSize) = apply {
            setTopRightCorner(RoundedCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.roundBottomLeftCorner(radius: CornerSize) = apply {
            setBottomLeftCorner(RoundedCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.roundBottomRightCorner(radius: CornerSize) = apply {
            setBottomRightCorner(RoundedCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.cutAllCorners(@Px size: Float) = apply {
            setAllCorner(CutCornerTreatment(), size)
        }
        @JvmStatic fun ShapedDrawableBuilder.cutTopLeftCorner(@Px radius: Float) = apply {
            setTopLeftCorner(CutCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.cutTopRightCorner(@Px radius: Float) = apply {
            setTopRightCorner(CutCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.cutBottomLeftCorner(@Px radius: Float) = apply {
            setBottomLeftCorner(CutCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.cutBottomRightCorner(@Px radius: Float) = apply {
            setBottomRightCorner(CutCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.cutAllCorners(size: CornerSize) = apply {
            setAllCorner(CutCornerTreatment(), size)
        }
        @JvmStatic fun ShapedDrawableBuilder.cutTopLeftCorner(radius: CornerSize) = apply {
            setTopLeftCorner(CutCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.cutTopRightCorner(radius: CornerSize) = apply {
            setTopRightCorner(CutCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.cutBottomLeftCorner(radius: CornerSize) = apply {
            setBottomLeftCorner(CutCornerTreatment(), radius)
        }
        @JvmStatic fun ShapedDrawableBuilder.cutBottomRightCorner(radius: CornerSize) = apply {
            setBottomRightCorner(CutCornerTreatment(), radius)
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

    @Px private var leftPadding = 0
    @Px private var topPadding = 0
    @Px private var rightPadding = 0
    @Px private var bottomPadding = 0

    fun setTint(@ColorInt color: Int) = apply { materialShapeDrawable.setTint(color) }
    fun setShadowColor(@ColorInt color: Int) = apply { materialShapeDrawable.setShadowColor(color) }
    fun setRippleColor(@ColorInt color: Int) = apply { rippleColor = color }
    fun setElevation(@Px elevation: Float) = apply { materialShapeDrawable.elevation = elevation }

    fun setAlpha(@IntRange(from = 0, to = 255) alpha: Int) = apply { this.alpha = alpha }

    fun setStroke(@Px width: Float, @ColorInt color: Int) = apply { materialShapeDrawable.setStroke(width, color) }

    @JvmOverloads
    fun setPadding(
        @Px left: Int = leftPadding, @Px top: Int = topPadding,
        @Px right: Int = rightPadding, @Px bottom: Int = bottomPadding
    ) = apply {
        leftPadding = left
        topPadding = top
        rightPadding = right
        bottomPadding = bottom
    }
    fun setAllPadding(@Px padding: Int) = apply { setPadding(padding, padding, padding, padding) }

    fun setTopLeftCorner(corner: CornerTreatment) = apply { modelBuilder.setTopLeftCorner(corner) }
    fun setTopLeftCorner(corner: CornerType) = apply { setTopLeftCorner(corner.cornerTreatment) }
    fun setTopRightCorner(corner: CornerTreatment) = apply { modelBuilder.setTopRightCorner(corner) }
    fun setTopRightCorner(corner: CornerType) = apply { setTopRightCorner(corner.cornerTreatment) }
    fun setBottomLeftCorner(corner: CornerTreatment) = apply { modelBuilder.setBottomLeftCorner(corner) }
    fun setBottomLeftCorner(corner: CornerType) = apply { setBottomLeftCorner(corner.cornerTreatment) }
    fun setBottomRightCorner(corner: CornerTreatment) = apply { modelBuilder.setBottomRightCorner(corner) }
    fun setBottomRightCorner(corner: CornerType) = apply { setBottomRightCorner(corner.cornerTreatment) }
    fun setAllCorner(corner: CornerTreatment) = apply { modelBuilder.setAllCorners(corner) }
    fun setAllCorner(corner: CornerType) = apply { setAllCorner(corner.cornerTreatment) }
    fun setTopLeftCorner(corner: CornerTreatment, @Px size: Float) = apply {
        setTopLeftCorner(corner)
        modelBuilder.setTopLeftCornerSize(size)
    }
    fun setTopLeftCorner(corner: CornerType, @Px size: Float) = apply { setTopLeftCorner(corner.cornerTreatment, size) }
    fun setTopRightCorner(corner: CornerTreatment, @Px size: Float) = apply {
        setTopRightCorner(corner)
        modelBuilder.setTopRightCornerSize(size)
    }
    fun setTopRightCorner(corner: CornerType, @Px size: Float) = apply { setTopRightCorner(corner.cornerTreatment, size) }
    fun setBottomLeftCorner(corner: CornerTreatment, @Px size: Float) = apply {
        setBottomLeftCorner(corner)
        modelBuilder.setBottomLeftCornerSize(size)
    }
    fun setBottomLeftCorner(corner: CornerType, @Px size: Float) = apply { setBottomLeftCorner(corner.cornerTreatment, size) }
    fun setBottomRightCorner(corner: CornerTreatment, @Px size: Float) = apply {
        setBottomRightCorner(corner)
        modelBuilder.setBottomRightCornerSize(size)
    }
    fun setBottomRightCorner(corner: CornerType, @Px size: Float) = apply { setBottomRightCorner(corner.cornerTreatment, size) }
    fun setAllCorner(corner: CornerTreatment, @Px size: Float) = apply {
        setAllCorner(corner)
        modelBuilder.setAllCornerSizes(size)
    }
    fun setAllCorner(corner: CornerType, @Px size: Float) = apply { setAllCorner(corner.cornerTreatment, size) }
    fun setTopLeftCorner(corner: CornerTreatment, size: CornerSize) = apply {
        setTopLeftCorner(corner)
        modelBuilder.setTopLeftCornerSize(size)
    }
    fun setTopLeftCorner(corner: CornerType, size: CornerSize) = apply { setTopLeftCorner(corner.cornerTreatment, size) }
    fun setTopRightCorner(corner: CornerTreatment, size: CornerSize) = apply {
        setTopRightCorner(corner)
        modelBuilder.setTopRightCornerSize(size)
    }
    fun setTopRightCorner(corner: CornerType, size: CornerSize) = apply { setTopRightCorner(corner.cornerTreatment, size) }
    fun setBottomLeftCorner(corner: CornerTreatment, size: CornerSize) = apply {
        setBottomLeftCorner(corner)
        modelBuilder.setBottomLeftCornerSize(size)
    }
    fun setBottomLeftCorner(corner: CornerType, size: CornerSize) = apply { setBottomLeftCorner(corner.cornerTreatment, size) }
    fun setBottomRightCorner(corner: CornerTreatment, size: CornerSize) = apply {
        setBottomRightCorner(corner)
        modelBuilder.setBottomRightCornerSize(size)
    }
    fun setBottomRightCorner(corner: CornerType, size: CornerSize) = apply { setBottomRightCorner(corner.cornerTreatment, size) }
    fun setAllCorner(corner: CornerTreatment, size: CornerSize) = apply {
        setAllCorner(corner)
        modelBuilder.setAllCornerSizes(size)
    }
    fun setAllCorner(corner: CornerType, size: CornerSize) = apply { setAllCorner(corner.cornerTreatment, size) }

    fun setLeftEdge(edge: EdgeTreatment) = apply { modelBuilder.setLeftEdge(edge) }
    fun setTopEdge(edge: EdgeTreatment) = apply { modelBuilder.setTopEdge(edge) }
    fun setRightEdge(edge: EdgeTreatment) = apply { modelBuilder.setRightEdge(edge) }
    fun setBottomEdge(edge: EdgeTreatment) = apply { modelBuilder.setBottomEdge(edge) }
    fun setAllEdge(edge: EdgeTreatment) = apply { modelBuilder.setAllEdges(edge) }

    override fun onCreateParams(): ShapeAppearanceModel {
        return modelBuilder.build()
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateResult(param: ShapeAppearanceModel?): ShapedDrawable {
        this.materialShapeDrawable.shapeAppearanceModel = param!!
        this.materialShapeDrawable.setPadding(leftPadding, topPadding, rightPadding, bottomPadding)

        var resultDrawable: Drawable = this.materialShapeDrawable

        rippleColor?.let {
            rippleShapeDrawable = MaterialShapeDrawable(param).apply {
                interpolation = materialShapeDrawable.interpolation
            }
            val colorStateList = ColorStateList(arrayOf(intArrayOf()), intArrayOf(it))
            val rippleDrawable = RippleDrawable(colorStateList, null, rippleShapeDrawable)
            resultDrawable = LayerDrawable(arrayOf(resultDrawable, rippleDrawable)).apply {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
                    setPadding(leftPadding, topPadding, rightPadding, bottomPadding)
            }
        }

        return ShapedDrawable(resultDrawable, this.materialShapeDrawable).apply {
            alpha = this@ShapedDrawableBuilder.alpha
        }
    }

    class WithContext constructor(private val context: Context) : ShapedDrawableBuilder(), ContextScope {
        constructor(context: ContextScope): this(context.getContext())
        override fun getContext(): Context {
            return context
        }
    }

}