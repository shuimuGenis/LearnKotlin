package com.example.kotlin

import android.util.Log

class HeightLevelFun {
    /**
     * 什么是高阶函数，就是 函数的参数/返回值是一个匿名函数的函数。
     * 含义就是 一个函数作为另一个函数的参数传入。或者 一个函数作为另一个函数的返回值
     */

    fun HegithLeveDemo() {
        val strArr = arrayListOf("hello", "mine", "genius", "shui", "marth")
        val actionFun = fun(item: String) {
            Log.i("zui", "item:$item")
        }
        strArr.forEach(actionFun)
    }

    fun heightLevelDemo2() {
        val strArr = arrayListOf("hello", "mine", "genius", "shui", "marth")
        strArr.forEach { Log.i("zui", "item:$it") }
    }

    fun heightLevelDemo3() {
        val strArr = arrayListOf("hello", "mine", "genius", "shui", "marth")
        strArr.forEach { Log.i("zui", "item $it") }
    }

    fun heightFunfilterDemo() {
        val strArr = arrayListOf("hello", "mine", "genius", "shui", "", "marth")
        val reulst = strArr.filter { it.isNotEmpty() }
        Log.i("zui", reulst.toString())
    }

    fun heightDemo4() {
        val numList = listOf(1, 2, 3, 4, 5, 6)
        val mutableNumList = mutableListOf<Int>()
        numList.forEach { mutableNumList.add(it * 2 + 1) }
        mutableNumList.forEach { Log.i("zui", "mutableList Item $it") }
    }

    fun heightDemoe5() {
        val numList = listOf(1, 2, 3, 4, 5, 6)
        val numString = numList.map {
            "$it hah"
        }
        Log.i("zui", numString.toString())
    }

    fun heightDemo6() {
        val numList = listOf(1, 2, 3, 4, 5, 6)
        //将list集合中的数字相加。获取总的结果
        //acc表示 计算的结果，i表示每个相加的item
        val result = numList.reduce { acc, i ->
            Log.i("zui", "acc $acc i $i")
            acc + i
        }
        Log.i("zui", result.toString())
    }

    fun heightDemo7() {
        val contentList = listOf("how", "are", "you", "?")
        val result = contentList.fold(StringBuilder()) { acc, item ->
            acc.append(" ").append(item)
        }
        Log.i("zui", result.delete(0, 1).toString())
    }

    fun getPersonInstance(): APerson? {
        return APerson("小红", 22)
    }

    /**
     * fun <T> T.apply(block: T.() -> kotlin.Unit): T
     * apply方法的定义表示：
     * T.apply表示 任意对象的拓展函数
     * 参数列表的含义表示:传入T对象的一个没有参数的匿名拓展函数，该匿名拓展函数没有返回值。
     */
    fun heightDemo8() {
        getPersonInstance()?.apply {
            logInstance("my name is $name , and this year is $age age")
        }
        val personInstance = getPersonInstance()
        personInstance.let {
            logInstance("let function is Call ,and the values is name = ${it?.name} ; age = ${it?.age}")
        }
    }
}

class CustomHeightFun {
    //自定义高阶函数，高阶函数的函数参数/返回值都可能是一个匿名函数
    //例如定义一个 返回值为匿名函数的高阶函数
    fun countAddHfun(): () -> Unit {
        return {
            Log.i("zui", "我是一个匿名函数，作为高阶函数返回值")
        }
    }

    //例如 定义一个高阶函数，返回值是一个匿名函数，该匿名函数有一个参数，并有返回值。
    fun computeIncreateHfun(x: Int): (Int) -> Int {
        //当这个匿名函数返回的时候，将会持有 传入进来的 X参数的引用。等这个匿名函数被调用的时候，将会使用到X参数。
        return fun(y: Int): Int {
            return x + y
        }
    }
}

data class APerson(val name: String, val age: Int)


/**
 * 直接定义在kotlin文件中的函数为顶层函数，顶层函数不属于类，是任何地方都可以使用的
 * 使用方法就是 通过 import 包名.函数名 导入函数，之后就可以正常使用顶层函数了。
 *
 * 科里化：把多个函数的操作转化成 单个一系列的函数去使用。
 * 能够实现科里化 完全是因为kotlin函数的返回值可以是一个函数。
 */
internal fun cerryTest(tag: String) =
    fun(msg: String) = Log.i("[${Thread.currentThread().name}]${tag}", msg)

/**
 * 这样就可以省略，tag前缀。执行传入内容部分即可
 * */
val logInstance = cerryTest("zui")

