package com.kekadoc.tools.android.shape

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.*
import com.kekadoc.tools.android.*
import com.kekadoc.tools.android.log.log
import com.kekadoc.tools.android.shaper.*
import com.kekadoc.tools.android.shaper.corners.RelativeCornerSize
import com.kekadoc.tools.android.shaper.corners.fullCornerSize
import com.kekadoc.tools.android.shaper.edges.SquareEdgeTreatment
import com.kekadoc.tools.android.view.ViewUtils.doOnMeasureView
import com.kekadoc.tools.value.ValueUtils
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG: String = "MainActivity-TAG"
    }

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
            measureTimeMillis { makeView_0() }.log().e(msg = "0 - ")
            measureTimeMillis { makeView_1() }.log().e(msg = "1 - ")
            measureTimeMillis { makeView_2() }.log().e(msg = "2 - ")
            measureTimeMillis { makeView_3() }.log().e(msg = "3 - ")
        }
    }

    /**
     * Edge Test
     */
    private fun makeView_0() {
        view_0!!.background = ShapedDrawableBuilder.createWithContext(this) {
            shape {
                setAllCorner(RoundedCornerTreatment(), dimen(R.dimen.dimen_corner_size))
                setAllEdges(SquareEdgeTreatment(dpToPx(44f), dpToPx(16f), dpToPx(4f)))
            }
            setTint(Color.YELLOW)
            setStroke(dpToPx(4f), Color.BLUE)
            setRippleColor(themeColor(android.R.attr.colorAccent))
            setElevation(dpToPx(14f))
            setShadowColor(color(R.color.BLACK))
            setPadding(dpToPx(3f).toInt())
            setAlpha(50)
        }.build()
    }

    /**
     * Corner Test
     */
    private fun makeView_1() {
        view_1!!.background = shapedDrawable {
            shape {
                //cutAllCorners(dimen(R.dimen.dimen_corner_size))
                setAllCornerSizes(fullCornerSize())
            }
            setTint(Color.YELLOW)
            setRippleColor(themeColor(android.R.attr.colorAccent))
            setElevation(dpToPx(14f))
            setShadowColor(color(R.color.Aqua))
            setPadding(dpToPx(16f).toInt())
            setInset(inset = dpToPx(16f).toInt())
            setAlpha(50)
        }
    }

    private fun makeView_2() {
        var negative = true
        view_2!!.setOnClickListener {
            runInterpolation {
                shapedDrawable?.let {
                    val change = 0.01f * (if (negative) -1.0f else 1.0f)
                    ValueUtils.addValueInRange(0f, 1f, it.getShapeDrawable()!!.interpolation, change, object : ValueUtils.RangeChangeEvents<Float> {
                        override fun onChange(oldValue: Float, newValue: Float) {
                            it.getShapeDrawable()?.interpolation = newValue
                        }
                        override fun onMax(max: Float) {
                            it.getShapeDrawable()?.interpolation = max
                            negative = !negative
                        }
                        override fun onMin(min: Float) {
                            it.getShapeDrawable()?.interpolation = min
                            negative = !negative
                        }
                        override fun onOverflow(overflow: Float) {}
                    })
                }
            }
        }

        shapedDrawable = shapedDrawable(asContextScope()) {
            shape {
                cutTopLeftCorner(dpToPx(64f))
                roundTopRightCorner(dpToPx(48f))
                cutBottomRightCorner(dpToPx(64f))
                roundBottomLeftCorner(dpToPx(48f))
            }
            setTint(Color.YELLOW)
            setStroke(dpToPx(6f), Color.BLUE)
            setRippleColor(themeColor(android.R.attr.colorAccent))
            setElevation(dpToPx(24f))
            setShadowColor(color(R.color.Red))
            setPadding(dpToPx(8f).toInt())
            setAlpha(255)
        }
        view_2!!.background = shapedDrawable
    }

    private fun makeView_3() {
        val model = ShapeAppearanceModel.builder()
            .setAllCornerSizes(RelativeCornerSize(0.5f))
            .setAllCorners(RoundedCornerTreatment())
            .build()
        view_3!!.background = MaterialShapeDrawable(model).apply {
            elevation = dpToPx(12f)
            setShadowColor(Color.MAGENTA)
            setTint(Color.GRAY)
            shadowCompatibilityMode = MaterialShapeDrawable.SHADOW_COMPAT_MODE_ALWAYS
            setUseTintColorForShadow(true)
        }
    }

    private fun makeCard() {}

    private fun makeFrame() {
       frameLayout!!.background = DrawableUtils.getRipple(Color.CYAN, Color.RED)
    }

    var job: Job? = null
    private fun runInterpolation(update: () -> Unit) {
        if (job == null) {
            job = GlobalScope.launch {
                while (true) {
                    withContext(Dispatchers.Main) {
                        update.invoke()
                    }
                    delay(10)
                }
            }
        } else {
            job?.cancel()
            job = null
        }

    }

}