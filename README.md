# shape-tools

```kotlin
val drawable = shapedDrawable(context) {
    shape {
        roundAllCorners(dpToPx(18f))
        setTopEdge(TriangleEdgeTreatment(dpToPx(24f), true))
    }
    setTint(Color.RED)
    setElevation(dpToPx(4f))
    setRippleColor(themeColor(ThemeColor.PRIMARY))
    setInset(
        left = dpToPx(4f).toInt(),
        top = dpToPx(8f).toInt(),
        right = dpToPx(4f).toInt(),
        bottom = dpToPx(8f).toInt(),
    )
    setPadding(dpToPx(4f).toInt())
    setStroke(dpToPx(4f), themeColor(ThemeColor.ACCENT))
    setShadowColor(Color.MAGENTA)
}
```
