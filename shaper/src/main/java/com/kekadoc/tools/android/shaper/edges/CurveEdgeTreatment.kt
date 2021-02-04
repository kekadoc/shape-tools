package com.kekadoc.tools.android.shaper.edges

import com.google.android.material.shape.EdgeTreatment
import com.google.android.material.shape.ShapePath

class CurveEdgeTreatment(private val bend: Float) : EdgeTreatment() {

    override fun getEdgePath(length: Float, center: Float,
        interpolation: Float, shapePath: ShapePath) {
        shapePath.quadToPoint(center, bend, length, 0f)
    }

}