package com.example.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    /**
     * kotlinx中给Android开发提供了一个MVVM的框架，如果我们想使用kotlinx的MVVM框架,
     * (1)添加kotlin插件: apply plugin:'kotlin-android-extensions'
     * (2)当我们想要找到xml布局文件中的控件时,通过：import kotlinx.android.synthetic.main.activity_login.* 表示导入这个布局中所有的控件id
     * (3)之后就可以直接通过控件ID来，操作控件了
     */
}
