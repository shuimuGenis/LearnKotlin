package com.example.kotlin

import android.util.Log

interface Driver{
    fun driver()
}

interface Writter{
    fun write()
}

class CarDriver : Driver {
    override fun driver() {
        Log.i("zui","我是司机，正在开车。。")
    }
}

class PPTWirter :Writter{
    override fun write(){
        Log.i("zui","我是策划，正在写PPT。。。")
    }
}

/**
 * Boss类对 Driver，Writter两个接口的实现 代理给了具体的类
 */
class Boss(private val driver: CarDriver, private val writer:PPTWirter):Driver by driver,Writter by writer