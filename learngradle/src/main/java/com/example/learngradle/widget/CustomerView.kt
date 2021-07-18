package com.example.learngradle.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc
 */
class CustomerView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        Log.i("zui", "父view传递过来的位置尺寸信息:l:$l,t:$t,r:$r,b:$b--->width:${r - l},height:${b - t}")
        val dp200 = px2dp(context, 200)
        val dp100 = px2dp(context, 100)
        super.layout(l + dp100, t, l + dp200 + dp100, t + dp200)
    }
}

fun px2dp(context: Context, dp: Int): Int {
    val scale = context.resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}