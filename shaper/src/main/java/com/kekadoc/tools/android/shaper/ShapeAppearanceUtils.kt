package com.kekadoc.tools.android.shaper

import androidx.annotation.Px
import com.google.android.material.shape.CornerTreatment
import com.google.android.material.shape.CutCornerTreatment
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapeAppearanceModel

fun ShapeAppearanceModel.Builder.roundAllCorners(@Px size: Float = 0f) {
    setAllCorners(ShapeAppearanceUtils.DEF_CORNER_ROUND)
    setAllCornerSizes(size)
}
fun ShapeAppearanceModel.Builder.cutAllCorners(@Px size: Float = 0f) {
    setAllCorners(ShapeAppearanceUtils.DEF_CORNER_CUT)
    setAllCornerSizes(size)
}

object ShapeAppearanceUtils {

    fun builder() = ShapeAppearanceModel.builder()

    val DEF_CORNER_CUT: CornerTreatment = CutCornerTreatment()
    val DEF_CORNER_ROUND: CornerTreatment = RoundedCornerTreatment()



}