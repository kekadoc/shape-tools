package com.kekadoc.tools.android.shaper.corners

import android.graphics.RectF
import androidx.annotation.FloatRange
import com.google.android.material.shape.AbsoluteCornerSize
import com.google.android.material.shape.CornerSize
import com.google.android.material.shape.ShapeAppearanceModel
import kotlin.math.min

fun ShapeAppearanceModel.Builder.zeroSize(): CornerSize {
    return AbsoluteCornerSize(0f)
}
fun ShapeAppearanceModel.Builder.fullSize(): CornerSize {
    return RelativeCornerSize(0.5f)
}

fun ShapeAppearanceModel.Builder.absoluteSize(float: Float): CornerSize {
    return AbsoluteCornerSize(float)
}
fun ShapeAppearanceModel.Builder.relativeSize(@FloatRange(from = 0.0, to = 1.0) float: Float): CornerSize {
    return RelativeCornerSize(float)
}

data class RelativeCornerSize(@FloatRange(from = 0.0, to = 1.0) val percent: Float) : CornerSize {
    override fun getCornerSize(bounds: RectF): Float {
        return percent * min(bounds.width(), bounds.height())
    }
}