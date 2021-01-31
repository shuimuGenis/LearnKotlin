package com.example.learngradle

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc
 */
class ShowActivity :Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)
    }
}