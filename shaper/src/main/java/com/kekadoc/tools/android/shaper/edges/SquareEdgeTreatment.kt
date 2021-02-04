package com.kekadoc.tools.android.shaper.edges

import com.google.android.material.shape.EdgeTreatment
import com.google.android.material.shape.ShapePath
import com.kekadoc.tools.android.shaper.endX
import com.kekadoc.tools.android.shaper.endY

class SquareEdgeTreatment(
    private val width: Float,
    private val height: Float,
    private val round: Float) : EdgeTreatment() {

    override fun getEdgePath(length: Float, center: Float, interpolation: Float, shapePath: ShapePath) {
        val round = round * interpolation
        val height = height * interpolation

        shapePath.apply {
            reset(0f, 0f)
            val toX = center - width / 2
            lineTo(toX - round, 0f)
            addArc(
                toX - round * 2,
                0f,
                toX * interpolation,
                2 * round * interpolation,
                270f,
                90f
            )
            quadToPoint(toX - round, round, toX, round)
            lineTo(shapePath.endX(), height)
            lineTo(shapePath.endX() + width, shapePath.endY())
            lineTo(shapePath.endX(), round)
            addArc(
                shapePath.endX() - 0,
                0f,
                shapePath.endX() + round * 2 * interpolation,
                2 * round * interpolation,
                180f,
                90f
            )
            lineTo(length, 0f)
        }
    }
}