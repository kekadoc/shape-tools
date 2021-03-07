package com.kekadoc.tools.android.shaper

import androidx.annotation.Px
import com.google.android.material.shape.*

fun ShapePath.startX() = startX
fun ShapePath.startY() = startY
fun ShapePath.endX() = endX
fun ShapePath.endY() = endY

fun ShapeAppearanceModel.Builder.roundAllCorners(@Px size: Float = 0f) = apply {
    setAllCorner(ShapeAppearanceUtils.DEF_CORNER_ROUND, size)
}
fun ShapeAppearanceModel.Builder.roundTopLeftCorner(@Px size: Float) = apply {
    setTopLeftCorner(RoundedCornerTreatment(), size)
}
fun ShapeAppearanceModel.Builder.roundTopRightCorner(@Px size: Float) = apply {
    setTopRightCorner(RoundedCornerTreatment(), size)
}
fun ShapeAppearanceModel.Builder.roundBottomLeftCorner(@Px size: Float) = apply {
    setBottomLeftCorner(RoundedCornerTreatment(), size)
}
fun ShapeAppearanceModel.Builder.roundBottomRightCorner(@Px size: Float) = apply {
    setBottomRightCorner(RoundedCornerTreatment(), size)
}

fun ShapeAppearanceModel.Builder.roundAllCorners(size: CornerSize) = apply {
    setAllCorner(RoundedCornerTreatment(), size)
}
fun ShapeAppearanceModel.Builder.roundTopLeftCorner(size: CornerSize) = apply {
    setTopLeftCorner(RoundedCornerTreatment(), size)
}
fun ShapeAppearanceModel.Builder.roundTopRightCorner(size: CornerSize) = apply {
    setTopRightCorner(RoundedCornerTreatment(), size)
}
fun ShapeAppearanceModel.Builder.roundBottomLeftCorner(size: CornerSize) = apply {
    setBottomLeftCorner(RoundedCornerTreatment(), size)
}
fun ShapeAppearanceModel.Builder.roundBottomRightCorner(size: CornerSize) = apply {
    setBottomRightCorner(RoundedCornerTreatment(), size)
}

fun ShapeAppearanceModel.Builder.cutAllCorners(@Px size: Float = 0f) = apply {
    setAllCorner(ShapeAppearanceUtils.DEF_CORNER_CUT, size)
}
fun ShapeAppearanceModel.Builder.cutTopLeftCorner(@Px radius: Float) = apply {
    setTopLeftCorner(CutCornerTreatment(), radius)
}
fun ShapeAppearanceModel.Builder.cutTopRightCorner(@Px radius: Float) = apply {
    setTopRightCorner(CutCornerTreatment(), radius)
}
fun ShapeAppearanceModel.Builder.cutBottomLeftCorner(@Px radius: Float) = apply {
    setBottomLeftCorner(CutCornerTreatment(), radius)
}
fun ShapeAppearanceModel.Builder.cutBottomRightCorner(@Px radius: Float) = apply {
    setBottomRightCorner(CutCornerTreatment(), radius)
}

fun ShapeAppearanceModel.Builder.cutAllCorners(size: CornerSize) = apply {
    setAllCorner(CutCornerTreatment(), size)
}
fun ShapeAppearanceModel.Builder.cutTopLeftCorner(size: CornerSize) = apply {
    setTopLeftCorner(CutCornerTreatment(), size)
}
fun ShapeAppearanceModel.Builder.cutTopRightCorner(size: CornerSize) = apply {
    setTopRightCorner(CutCornerTreatment(), size)
}
fun ShapeAppearanceModel.Builder.cutBottomLeftCorner(size: CornerSize) = apply {
    setBottomLeftCorner(CutCornerTreatment(), size)
}
fun ShapeAppearanceModel.Builder.cutBottomRightCorner(size: CornerSize) = apply {
    setBottomRightCorner(CutCornerTreatment(), size)
}

fun ShapeAppearanceModel.Builder.setTopLeftCorner(corner: CornerTreatment, @Px size: Float) = apply {
    setTopLeftCorner(corner)
    setTopLeftCornerSize(size)
}
fun ShapeAppearanceModel.Builder.setTopRightCorner(corner: CornerTreatment, @Px size: Float) = apply {
    setTopRightCorner(corner)
    setTopRightCornerSize(size)
}
fun ShapeAppearanceModel.Builder.setBottomLeftCorner(corner: CornerTreatment, @Px size: Float) = apply {
    setBottomLeftCorner(corner)
    setBottomLeftCornerSize(size)
}
fun ShapeAppearanceModel.Builder.setBottomRightCorner(corner: CornerTreatment, @Px size: Float) = apply {
    setBottomRightCorner(corner)
    setBottomRightCornerSize(size)
}
fun ShapeAppearanceModel.Builder.setAllCorner(corner: CornerTreatment, @Px size: Float) = apply {
    setAllCorners(corner)
    setAllCornerSizes(size)
}

fun ShapeAppearanceModel.Builder.setTopLeftCorner(corner: CornerTreatment, size: CornerSize) = apply {
    setTopLeftCorner(corner)
    setTopLeftCornerSize(size)
}
fun ShapeAppearanceModel.Builder.setTopRightCorner(corner: CornerTreatment, size: CornerSize) = apply {
    setTopRightCorner(corner)
    setTopRightCornerSize(size)
}
fun ShapeAppearanceModel.Builder.setBottomLeftCorner(corner: CornerTreatment, size: CornerSize) = apply {
    setBottomLeftCorner(corner)
    setBottomLeftCornerSize(size)
}
fun ShapeAppearanceModel.Builder.setBottomRightCorner(corner: CornerTreatment, size: CornerSize) = apply {
    setBottomRightCorner(corner)
    setBottomRightCornerSize(size)
}
fun ShapeAppearanceModel.Builder.setAllCorner(corner: CornerTreatment, size: CornerSize) = apply {
    setAllCorners(corner)
    setAllCornerSizes(size)
}

object ShapeAppearanceUtils {

    fun builder() = ShapeAppearanceModel.builder()

    val DEF_CORNER_CUT: CornerTreatment = CutCornerTreatment()
    val DEF_CORNER_ROUND: CornerTreatment = RoundedCornerTreatment()

}