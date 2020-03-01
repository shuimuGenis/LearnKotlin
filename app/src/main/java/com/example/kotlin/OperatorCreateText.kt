package com.example.kotlin

import android.util.Log

/*在学习匿名函数的时候，我们通过一个变量来引用匿名函数，并通过系统的invoke操作符来调用匿名函数。
既然invoke是系统的操作符 那么我们可以重写系统的操作符吗?是可以的,通过operator关键字来重写系统的操作符
注意：重写的系统操作符/定义的操作符 只是对这个定义的类生效。
 */
class CreateOperate {
    var tempValue: Int = 0;
    /*
    重写系统的操作符，这个是+ 号的操作符
    重写的时候 方法名 参数列表 等必须和系统的保持一致，否则报错，
     */
    operator fun plus(other: Int): Int {
        return tempValue + other
    }

    /**
     * 重写系统的操作符 这个是 * 号操作符
     */
    operator fun times(times: Int): Int {
        return tempValue * times
    }
    /*
    系统可以定义操作符 那么我们可以自定义新的操作符吗？也是可以的
    自定义全新的操作符 通过infix关键字来定义
    使用的时候
    xxx say xxx
    xxx lalala xxx
    注意:infix定义的操作符方法 方法的参数只能是一个
     */

    infix fun say(msg: String) = "helow $msg"
    infix fun lalala(msg: String) {
        Log.i("zui", "$msg")
    }


}

/**
 * 类的扩展 格式很简单：(各种修饰符) fun 类.方法名(参数列表){}
通过类扩展定义的方法,就好像这个类本身就定义了该方法一样。
注意：扩展方法不可以定义在 类的内部 如果定义的扩展方法无效，检查是否不小心把扩展方法写在类里面了
 */
fun String.cxIsNotEmpty(): Boolean {
    return length > 0
}

/**
 * 匿名拓展函数
 * 一下就声明了一个 匿名拓展函数。匿名的函数都必须要有一个变量去引用它。
 */
val test = fun String.() {}

/**
 * 定义一个复合函数的模板
 * Function代表任意函数。
 * Function1<P1, P2>.compleFix(subFunction: Function1<P2, R>)表示给任意函数添加一个拓展函数compleFix方法
 * compleFix(subFunction: Function1<P2, R>)表示该拓展函数的传入的 参数 是一个函数。
 * 最后"Function1<P1, R>" 表示返回一个函数。
 */
infix fun <P1, P2, R> Function1<P1, P2>.compleFix(subFunction: Function1<P2, R>): Function1<P1, R> {
    return fun(P1): R {
        return subFunction(this(P1))
    }
}

val add5 = fun(x: Int): Int = x + 5
val plus3 = fun(x: Int): Int = x * 3