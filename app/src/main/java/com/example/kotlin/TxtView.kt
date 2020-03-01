package com.example.kotlin

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView

class TxtView : TextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, intArray: Int) : super(context, attributeSet, intArray)

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.i("zui", "view dispatchTouchEvent")
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.i("zui", "onTouchEvent")
        return true
    }
}