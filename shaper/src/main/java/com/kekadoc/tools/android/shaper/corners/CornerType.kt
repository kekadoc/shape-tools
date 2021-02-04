package com.kekadoc.tools.android.shaper.corners

import com.google.android.material.shape.CornerTreatment
import com.kekadoc.tools.android.shaper.ShapeAppearanceUtils

enum class CornerType(val cornerTreatment: CornerTreatment) {

    CUT(ShapeAppearanceUtils.DEF_CORNER_CUT),
    ROUND(ShapeAppearanceUtils.DEF_CORNER_ROUND);

}