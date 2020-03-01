package com.example.kotlin

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.LinearLayout

class TxtFrameLayout : LinearLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

   /* override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (MotionEvent.ACTION_DOWN == ev?.action) {
            Log.i("zui", "ViewGroup onIntercepterTouchEvent false")
            super.onInterceptTouchEvent(ev)
        } else {
            Log.i("zui", "ViewGroup onIntercepterTouchEvent true")
            true
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i("zui", "viewgroup onTouchEvent")
        return super.onTouchEvent(event)
    }*/
}