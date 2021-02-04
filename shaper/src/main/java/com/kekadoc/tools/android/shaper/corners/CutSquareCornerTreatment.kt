package com.kekadoc.tools.android.shaper.corners

import android.graphics.RectF
import com.google.android.material.shape.CornerSize
import com.google.android.material.shape.CornerTreatment
import com.google.android.material.shape.ShapePath
import com.kekadoc.tools.android.shaper.endX
import com.kekadoc.tools.android.shaper.endY

class CutSquareCornerTreatment : CornerTreatment() {

    override fun getCornerPath(shapePath: ShapePath, angle: Float, interpolation: Float,
        bounds: RectF, size: CornerSize) {

        val sizeCorner = size.getCornerSize(bounds) * interpolation
        shapePath.apply {
            reset(0f, sizeCorner)
            lineTo(endX() + sizeCorner, endY())
            lineTo(endX(), 0f)
        }
    }

}