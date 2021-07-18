package com.learn.kotlin

import KnowledgePoints04
import UserInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learn.kotlin.bean.Student
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch {
            println("开始 ")
            println("结束 ")
        }
        Student().haiBury("")
        Student().apply {}
        Student().let { it.name }
        var list = ArrayList<Student>()
    }
}

fun UserInfo.haiBury(content: String) {
    print("当前对象:${this}")
}

inline fun <T> T.applyTest(block: T.() -> Unit): T {
    block()
    return this
}