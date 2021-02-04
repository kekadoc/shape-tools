package com.kekadoc.tools.android.shaper.corners

import android.graphics.RectF
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment
import com.google.android.material.shape.CornerSize
import com.google.android.material.shape.CornerTreatment
import com.google.android.material.shape.ShapePath
import com.kekadoc.tools.android.log.log

class CutRoundedCornerTreatment(private val roundFraction: Float = 0.5f) : CornerTreatment() {

    override fun getCornerPath(shapePath: ShapePath, angle: Float, interpolation: Float, bounds: RectF, size: CornerSize) {
        val cornerSize = size.getCornerSize(bounds) * interpolation
        val roundCut = (cornerSize * 0.5f) * roundFraction

        shapePath.apply {
            reset(0f, cornerSize + roundCut, 0f, 0f)
            quadToPoint(0f, cornerSize, roundCut, cornerSize - roundCut)
            quadToPoint(cornerSize / 2, cornerSize / 2, cornerSize - roundCut, roundCut)
            quadToPoint(cornerSize, 0f, cornerSize + roundCut, 0f)
        }

    }

}