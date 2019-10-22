package me.sample.orders.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class ShadowView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {
    
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        return true
    }
}