package com.example.java2kotlin

import android.app.Activity
import android.view.View
import android.view.ViewGroup

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc
 */
open class TestFather {

    @Suppress("UNCHECKED_CAST")
    open fun <T : TestFather> attach(ac: Activity): T {
        val viewGroup = ac.findViewById<ViewGroup>(android.R.id.content)
        initView(viewGroup)
        return this as T
    }

    open fun initView(view: View) {}
}