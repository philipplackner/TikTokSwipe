package com.androiddevs.tiktokswipe

import android.content.Context
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager
import kotlin.math.abs
import kotlin.math.max

private const val MIN_SCALE = 0.8f
private const val MIN_ALPHA = 0.7f

class VerticalViewPager(
    c: Context
) : ViewPager(c) {

    init {
        setPageTransformer(true, VerticalPageTransformer())
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun canScrollHorizontally(direction: Int) = false

    private fun swapXY(ev: MotionEvent) =
        ev.apply {
            setLocation(
                (ev.y / height) * width,
                (ev.x / width) * height
            )
        }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        val intercepted = super.onInterceptTouchEvent(swapXY(ev ?: return false))
        swapXY(ev)
        return intercepted
    }

    override fun onTouchEvent(ev: MotionEvent?) = super.onTouchEvent(swapXY(ev!!))

    inner class VerticalPageTransformer : PageTransformer {
        override fun transformPage(page: View, position: Float) {
            page.apply {
                when {
                    position < -1 -> {
                        alpha = 0f
                    }
                    position <= 1 -> {
                        val scaleFactor = max(MIN_SCALE, 1 - abs(position))

                        translationX = width * position * (-1)
                        translationY = height * position

                        scaleX = scaleFactor
                        scaleY = scaleFactor

                        alpha = (MIN_ALPHA +
                                (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))
                    }
                    else -> {
                        alpha = 0f
                    }
                }
            }
        }
    }
}