package com.example.kotlin

import android.util.Log

/**
 * 在java中接口是不可以定义属性的，只可以定义常量。
 * 在kotlin中接口可以定义属性，以及明确属性的类型，但是不可以给属性赋值，可以定义方法 或者抽象方法，
 * 定义的方法与java定义的方法差不多。在kotlin中认为 接口中定义的是一个规范，因此
 * 接口中的属性 只可以定义属性名 以及属性类型 不可以定义具体的属性值；方法的话 参考java即可
 */
interface USB {
    /**
     * 定义属性，实现该接口的子类都必须实现这个属性
     * 接口中的属性不可以进行赋值
     */
    var compomentName: String?
    /**
     * 定义方法
     */
   fun run()
}

class mouse : USB {
    /**
     * 实现接口时，不管属性 还是 方法，都必须用override修饰
     * var修饰的属性 能被var/val修饰的属性重写
     * val修饰的属性 只能被val修饰的属性重写
     */
    override var compomentName: String? = "鼠标"

    override fun run() {
        Log.i("zui", "$compomentName 已经正确装载")
    }
}

class keyboard : USB {
    override var compomentName: String? = "键盘"
    override fun run() {
        Log.i("zui", "$compomentName 已经正确装载")
    }
}

class computer {
    private var USBList: MutableList<USB> = mutableListOf()

    fun addUSB(usb: USB) {
        USBList.add(usb)
    }

    fun startRunning(){
        for (item in USBList){
            item.run()
        }
    }
}