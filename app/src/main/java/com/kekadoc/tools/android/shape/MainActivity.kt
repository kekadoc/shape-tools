package com.kekadoc.tools.android.shape

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.RippleDrawable
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.RoundedCornerTreatment
import com.google.android.material.shape.ShapeAppearanceModel
import com.kekadoc.tools.android.*
import com.kekadoc.tools.android.log.log
import com.kekadoc.tools.android.shaper.*
import com.kekadoc.tools.android.shaper.ShapedDrawableBuilder.Companion.cutAllCorners
import com.kekadoc.tools.android.shaper.corners.CutCircleCornerTreatment
import com.kekadoc.tools.android.shaper.corners.CutRoundedCornerTreatment
import com.kekadoc.tools.android.shaper.corners.CutSquareCornerTreatment
import com.kekadoc.tools.android.shaper.edges.DefEdgeTreatment
import com.kekadoc.tools.android.shaper.edges.CurveEdgeTreatment
import com.kekadoc.tools.android.shaper.edges.SquareEdgeTreatment
import com.kekadoc.tools.android.view.ViewUtils.doOnMeasureView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity-TAG"

    var shapedDrawable: ShapedDrawable? = null

    private var cardView: MaterialCardView? = null
    private var frameLayout: FrameLayout? = null

    private var view_0: View? = null
    private var view_1: View? = null
    private var view_2: View? = null
    private var view_3: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cardView = findViewById(R.id.materialCardView)
        frameLayout = findViewById(R.id.frameLayout)
        view_0 = findViewById(R.id.view_0)
        view_1 = findViewById(R.id.view_1)
        view_2 = findViewById(R.id.view_2)
        view_3 = findViewById(R.id.view_3)

        cardView!!.doOnMeasureView {
            makeCard()
            makeFrame()
            measureTimeMillis { makeView_3() }.log().e(msg = "- - ")
            measureTimeMillis { makeView_3() }.log().e(msg = "3 - ")
            measureTimeMillis { makeView_2() }.log().e(msg = "2 - ")
            measureTimeMillis { makeView_1() }.log().e(msg = "1 - ")
            measureTimeMillis { makeView_0() }.log().e(msg = "0 - ")
        }
    }

    private fun makeView_0() {
        view_0!!.background = ShapedDrawableBuilder.createWithContext(this) {
            setAllCorner(RoundedCornerTreatment(), dimen(R.dimen.dimen_corner_size))
            setAllEdge(SquareEdgeTreatment(dpToPx(44f), dpToPx(16f), dpToPx(4f)))
            setTint(Color.YELLOW)
            setStroke(dpToPx(2f), Color.BLUE)
            setRippleColor(themeColor(android.R.attr.colorAccent))
            setElevation(dpToPx(14f))
            setShadowColor(color(R.color.BLACK))
            setAllPadding(dpToPx(3f).toInt())
            setAlpha(50)
            cutAllCorners(dimen(R.dimen.dimen_corner_size))
        }.build()
        view_0!!.elevation = 100f
    }

    private fun makeView_1() {
        view_1!!.background = ShapedDrawableBuilder.createWithContext(this) {
            //setAllCorner(RoundedCornerTreatment(), dimen(R.dimen.dimen_corner_size))
            //setAllEdge(CurveEdgeTreatment(30f))
            setTint(Color.YELLOW)
            setStroke(dpToPx(2f), Color.BLUE)
            setRippleColor(themeColor(android.R.attr.colorAccent))
            setElevation(dpToPx(14f))
            setShadowColor(color(R.color.Aqua))
            setAllPadding(dpToPx(16f).toInt())
            setAlpha(50)
            cutAllCorners(dimen(R.dimen.dimen_corner_size))
        }.build()
    }

    private fun makeView_2() {
        view_2!!.setOnClickListener {
            runInterpolation {
                shapedDrawable?.setInterpolation(it)
            }
        }
        shapedDrawable = ShapedDrawableBuilder.createWithContext(this) {
            setAllCorner(CutRoundedCornerTreatment(1f), dimen(R.dimen.dimen_corner_size))
            setAllEdge(DefEdgeTreatment())
            setTint(Color.YELLOW)
            setStroke(dpToPx(6f), Color.BLUE)
            setRippleColor(themeColor(android.R.attr.colorAccent))
            setElevation(dpToPx(14f))
            setShadowColor(color(R.color.Aqua))
            setAllPadding(dpToPx(16f).toInt())
            setAlpha(50)
            //cutAllCorners(dimen(R.dimen.dimen_corner_size))
        }.build()
        view_2!!.background = shapedDrawable
    }

    private fun makeView_3() {
        val model = ShapeAppearanceModel.builder()
            .setAllCornerSizes(160f)
            .setAllCorners(CutRoundedCornerTreatment(0.4f))
            //.setAllEdges(DefEdgeTreatment())
            .build()
        val shapeDrawable = MaterialShapeDrawable(model).apply {
            elevation = 110f
            setShadowColor(Color.MAGENTA)
            setTint(Color.YELLOW)
            //shadowCompatibilityMode = MaterialShapeDrawable.SHADOW_COMPAT_MODE_DEFAULT
            shadowCompatibilityMode = MaterialShapeDrawable.SHADOW_COMPAT_MODE_ALWAYS
            //shadowCompatibilityMode = MaterialShapeDrawable.SHADOW_COMPAT_MODE_NEVER
            shadowRadius = 122;
            setUseTintColorForShadow(true)
            requiresCompatShadow().log().e(msg = "requiresCompatShadow: ")
        }
        //val shapeDrawable1 = MaterialShapeDrawable(model)
        //shapeDrawable1.setTint(Color.YELLOW);
        //val colorStateList = ColorStateList(arrayOf(intArrayOf()), intArrayOf(Color.RED))
        //val rippleDrawable = RippleDrawable(colorStateList, null, shapeDrawable1)
        //rippleDrawable.setPaddingMode(LayerDrawable.PADDING_MODE_NEST);
        //val layerDrawable = LayerDrawable(arrayOf(shapeDrawable, rippleDrawable))
        view_3!!.background =  shapeDrawable
    }

    private fun makeCard() {}
    private fun makeFrame() {
        frameLayout!!.setBackgroundColor(Color.YELLOW)
        frameLayout!!.background = resources.getDrawable(R.drawable.none, theme)
    }


    private fun runInterpolation(update: (interpolation: Float) -> Unit) {
        GlobalScope.launch {
            val timer = flow { // flow builder
                for (i in 0..100) {
                    delay(10)
                    emit(i)
                }
            }
            timer.collect {
                withContext(Dispatchers.Main) {
                    update.invoke(it / 100f)
                }
            }
        }
    }

}