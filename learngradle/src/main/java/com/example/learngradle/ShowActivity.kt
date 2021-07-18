package com.example.learngradle

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import kotlin.math.log

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc
 */
class ShowActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
    }

    fun onTab(view: View) {
        Log.i("zui", "measureWidth->${view.measuredWidth}  measureHeight->${view.measuredHeight}");
        Log.i("zui", "width->${view.width}  height->${view.height}");
    }
}