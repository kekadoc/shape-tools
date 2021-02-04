package com.kekadoc.tools.android.shaper.edges

import com.google.android.material.shape.EdgeTreatment
import com.google.android.material.shape.ShapePath
import com.kekadoc.tools.android.shaper.endY

/**
 * For supporting shadow.
 * */
class DefEdgeTreatment: EdgeTreatment() {

    override fun getEdgePath(length: Float, center: Float, interpolation: Float, shapePath: ShapePath) {
        shapePath.apply {
            reset(0f, 0.00001f)
            lineTo(length, endY())
        }
    }

}