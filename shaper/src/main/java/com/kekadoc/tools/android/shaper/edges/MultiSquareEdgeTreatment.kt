package com.kekadoc.tools.android.shaper.edges

import com.google.android.material.shape.EdgeTreatment
import com.google.android.material.shape.ShapePath
import com.kekadoc.tools.android.shaper.endX
import com.kekadoc.tools.android.shaper.endY

class MultiSquareEdgeTreatment(private val depth: Float, private val count: Int) : EdgeTreatment() {

    override fun getEdgePath(length: Float, center: Float, interpolation: Float, shapePath: ShapePath) {
        val countSpaces = count * 2 + 1
        val sizeOne = length / countSpaces
        val depth = this.depth * interpolation

        shapePath.apply {
            reset(0f, 0 * interpolation, 180f, 180f)
            for (i in 0 until count) {
                lineTo(endX() + sizeOne, 0f)
                lineTo(endX(), depth)
                lineTo(endX() + sizeOne, endY())
                lineTo(endX(), 0f)
            }
            lineTo(length, 0f)
        }

    }

}