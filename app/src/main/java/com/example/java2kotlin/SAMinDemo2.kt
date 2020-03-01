package com.example.java2kotlin

import com.example.kotlin.logInstance

/**
 * 定义一个 无参数无返回值的匿名函数 的别名为 NotParamsFun
 */
typealias NotParamsFun = () -> Unit

class SAMinDemoKotlin {
    /**
     * 保存的都是 别名为 NotParamsFun的匿名函数
     */
    private val runnableList = ArrayList<NotParamsFun>()
//    private val runnableList = ArrayList<Runnable>()

    /**
     * 方法的参数为 传入某种类型的匿名函数 。目前定义的是传入 无参数无返回值的匿名函数
     * 很简单.
     * (1 为这种形式 ()->Unit 的lambda表达式起一个别名，别名可以是任何名字，假设叫做NotParamsFun
     * (2 原先储存Runnable对象的集合，改为储存 "这种形式 ()->Unit 的lambda表达式" 的集合。
     * 定义为ArrayList<NotParamsFun>()
     * (3 把NotParamsFun当做新的对象函数 使用即可
     */
    fun addTask(run: NotParamsFun) {
        runnableList.add(run)
        logInstance("samindemo kotlin by add： count  ${runnableList.size} toStrng ${runnableList}")
    }

    /**
     * 方法的参数为 传入某种类型的匿名函数 。目前定义的是传入 无参数无返回值的匿名函数
     */
    fun romoveTask(run: NotParamsFun) {
        runnableList.remove(run)
        logInstance("samindemo kotlin by remove： count  ${runnableList.size} toStrng ${runnableList}")
    }

    /**
     * 这里声明了 传入的是Runnable对象。因此调用的时候 也不行传入对象，而不是表达式。
     * 传入的object:Runnable{
     *   ovverider fun run(){}
     * }
     * 是可以简写成 Runnable{}..但是虽然简写了，但是这个仍然代表一个匿名对象，而不是一个lamda表达式
     */
    fun testDemoInterface(run: Runnable) {

    }
}