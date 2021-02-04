package com.kekadoc.tools.android.shaper.corners

import com.google.android.material.shape.CornerTreatment
import com.google.android.material.shape.ShapePath
import android.graphics.RectF
import com.google.android.material.shape.CornerSize

class CutCircleCornerTreatment : CornerTreatment() {

    override fun getCornerPath(shapePath: ShapePath, angle: Float, interpolation: Float, bounds: RectF, size: CornerSize) {
        val sizeCorner = size.getCornerSize(bounds) * interpolation
        shapePath.reset(0f, sizeCorner)
        shapePath.quadToPoint(sizeCorner, sizeCorner, sizeCorner, 0f)
    }

}