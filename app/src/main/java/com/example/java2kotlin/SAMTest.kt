package com.example.java2kotlin

import com.example.kotlin.logInstance

class SAmTest {
    /**
     * 演示  kotlin 调用 java环境时的情况
     */
    fun SAMTest1() {
        val saMinDemoJava = SAMinDemoJava()
        /*val r = {
            logInstance("创建一个Runnable")
            Unit
        }
        kotlin 可以对接口函数用lambda表达式来代替，所以我们尝试直接传 lambda表达式给java方法
        这个例子中传入的 lambda表达式为:()->Unit
        saMinDemoJava.addTask(r)
        saMinDemoJava.addTask(r)
        saMinDemoJava.removeTask(r)
        saMinDemoJava.removeTask(r)
        *//*
        *从打印的结果看：
        * runnable list count by add 1
        * runnable list count by add 2
        * runnable list count by remove 2
        * runnable list count by remove 2
        * 集合正常添加了 但是无法 移除
        *
        * 原因:对于java的接口函数，kotlin这边如果使用 lambda表达式传递的话，实际上是每次都会生成一个新的接口对象的。
        * 包括进行移除的时候 也会生成新的接口对象
        */

        /* 因此，kotlin调用java的方法，为了保证程序正常，就不要传入lambda表达式了
         改为传入对象
         val r = object : Runnable {
             override fun run() {
             }
         }
         上面创建匿名对象的写法可以简化：
         (1:去掉 object：Runnable,保留{}花括号
         (2:去掉 override fun run
         (3:接口方法如果没有参数，也可以省略方法的小括号()
         (4:方法体如果只有一行代码,可以省略方法体的花括号{}
         (5:上面四步，最终简化成了lambda表达式，但是我们要创建对象，所以在第一步骤的时候 保留Runnable
         因此，最后写法：
         val r =Runnable{}
         */
        //这里代表了声明了一个 匿名对象，后面集合添加的都是同一个匿名对象 内存地址都一样
        val r = Runnable { logInstance("我就说不一样的烟火") }
        saMinDemoJava.addTask(r)
        saMinDemoJava.addTask(r)
        saMinDemoJava.removeTask(r)
        saMinDemoJava.removeTask(r)
        /*最后的 打印结果为
        *runnable list count by add 1
        *runnable list count by add 2
        *runnable list count by remove 1
        *runnable list count by remove 0
        *可以看出 正常移除了对象
        */
    }

    /**
     * 演示kotlin调用kotlin环境下的情况
     */
    fun SAMTest2() {
        val saMinDemoKotlin = SAMinDemoKotlin()
        /**
         * 声明一个匿名函数
         */
        val r = {
            logInstance("我就是一个 ()->Unit 形式的 lambda表达式对象")
            Unit
        }
        /**
         * 这里传入的都是匿名函数了。因为传入的是匿名函数，根据该方法的定义,任意的匿名函数都可以，
         * 只要该匿名函数满足 无参数无返回值即可
         */
        saMinDemoKotlin.addTask(r)
        saMinDemoKotlin.addTask(fun (){})
        saMinDemoKotlin.addTask(fun (){ logInstance("hahah ")})
        saMinDemoKotlin.addTask(r)
        saMinDemoKotlin.romoveTask(r)
        saMinDemoKotlin.romoveTask(r)
        /**
         * 这里传入的是匿名对象
         */
        val testRunnable = Runnable { logInstance("我是匿名对象来的哦") }
        saMinDemoKotlin.testDemoInterface(testRunnable)
    }
}
