package com.example.kotlin

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Button
import java.util.*


/**
 * 在kotlin中lambda表达式 和匿名函数是两个不同的概念。但是在kotlin中它们常常是可以相互通用的，但是在reture关键字的用法上lambda表达式和匿名函数就要区分开来了。
 * 在kotlin中，lambda表达式是无法通过reture关键字直接退出外部函数的，匿名函数也一样无法影响，lambda表达式只能退出lambda内部的流程，无法影响到外部
 * 但是如果，inline关键字和lambda表达式一起使用就会影响到外部函数的流程了，因为inline关键字直接把lambda表达式的函数体中的语句 替换到调用lambda表达式的位置，这样就
 * 相当于 这些语句本身就是写在 这个外部函数中的，所以就能影响到外部函数的流程。
 *
 * lambda表达式是一个表达式，因此它一定会有返回值的，通常最后一行就是lambda表达式的返回值。
 *
 * lambda表达式的完整语法是:
 * val/var 函数名 : (参数名1:参数1类型, 参数名2：参数2类型, ...) -> 返回值类型 = { 参数1, 参数2, ... -> 函数体 }
 * 返回值类型自动推导：
 * val/var 函数名 = { 参数1:类型1, 参数2:类型2, ... -> 函数体 }
 * 示例：
 * val sum = { x: Int, y: Int -> x + y; } // 常量sum被自动推导类型为: (x:Int,y:Int)->Int 的匿名函数。
 *
 * 参数只有一个的时候，可以用it关键字代替该参数
 * val/var 函数名={
 *   it.参数的各种方法()
 *   ...
 * }
 * 无参数形式为：
 * val/var 函数名 = { 函数体 }
 *
 * (x:Int,y:Int)->Boolean 我们可以理解为：两层意思:(1)你可以传入一个有两个Int类型参数且返回值是Boolean类型的函数(2)你可以传入一个两个Int类型参数且返回值是Boolean类型表达式
 *
 * 显示的指明变量的值:
 * val s8um: (x: Int) -> Boolean = { x -> x > 0 }
 * params表示参数列表，expressions表示具体实现，可以是单行语句，也可以是多行语句
 * lambda表达式虽然是会自动类型推导，但是我们还是遵循该原则:[声明lambda表达式时：如果在左边定义中，已经写了具体的类型声明，后面的实现就可以不用；反之，实现中则需要具体的声明]
 * 当然啦，基本上我们使用lambda表达式时是不用声明类型的，全靠自动推导。
 * 注意：如果一个lambda表达式只有一个参数，这个参数可以使用it指代，注意只能使用it指代，不能使用其它的单词代替，这点要谨记
 * 示例代码：
 *
val condition: (x: Int) -> Boolean = { x -> x > 0 }  //左边定义了具体类型，实现时就不用具体类型了
// 等价于（注意：这里只能用it，不能使用其它单词）
val condition: (x: Int) -> Boolean = { it > 0 }  //左边定义了具体类型，实现时就不用具体类型了
 *
 * 作为Java语言的近亲，kotlin的lambda表达式的语法和Java8的lambda非常接近。不过，由于Kotlin语言天然支持函数式编程的特性。声明lambda表达式不需要显式声明函数式接口
 * 大多数情况下，lambda表达式和匿名函数，匿名对象 都可以相互替换等价，但是在不同场景下还是不同的
 * lambda表达式 与匿名函数的不同是 lambda表达式的返回值是编译器自动推导的，而 匿名函数的返回值是显示声明的。
 *
 * lambda 和 匿名函数在 return关键字上是有不同的限制的。
 */
class lambdaTest {
    /**
     * 这里定义一个普通的方法
     * 默认public 修饰 final修饰
     * 方法定义格式为：fun 方法名(参数列表):返回值{方法体}
     */
    private fun addTest(a: Int, b: Int): Int {
        return a + b
    }

    val s8um: (x: Int) -> Boolean = { x -> x > 0 }
    val sum = { x: Int, y: Int -> x + y; }
    val condition = { x: Int, y: Int -> x > 0 }

    /**
     * 传入lambda表达式或者 传入一个函数
     * 在kotlin中，lambda表达式是无法通过reture关键字直接退出外部函数的，匿名函数也一样无法影响，lambda表达式只能退出lambda内部的流程，无法影响到外部
     * 但是如果，inline关键字和lambda表达式一起使用就会影响到外部函数的流程了，因为inline关键字直接把lambda表达式的函数体中的语句 替换到调用lambda表达式的位置，这样就
     * 相当于 这些语句本身就写在 这个外部函数中国，所以就能影响到外部函数的流程。
     */
    fun testLambdaA() {
        // inline关键字，因此 lambda表达式的reture将会直接退出testLambdaA()方法，去过去掉inline关键字，那就无法影响了。
        lamdbA {
            logInstance("我是lambda表达式, return@lamdbA")
            return
        }
        logInstance("打印了吗")
    }

    /**
     *
     */
    inline fun lamdbA(bloc: (Boolean) -> Unit) {
        bloc(true)
        logInstance("lamdbA 打印了吗")
    }

    /**
     * 定义一个匿名函数
     */
    val notNameFun = fun(a: Int, b: Int) = a + b

    /**
     * 使用匿名函数
     */
    fun computeFun(va1: Int, va2: Int, block: (Int, Int) -> Int): Int {
        return block(va1, va2)
    }

    /**
     * object关键字可以声明一个匿名对象
     */
    fun lambdaDemo() {

        //开启一个线程，定义一个匿名对象。
        Thread(object : Runnable {
            override fun run() {

                Log.i("zui", "thread running 。。。。")
            }
        }).start()

        Thread(fun() {
            logInstance("确实可以");
        })
    }

    /**
     * 对上面的匿名对象进行简化
     * 1:去掉 object :Runnable,留下匿名对象的花括号
     * 2:去掉 override fun run
     * 3:如果参数列表没有参数，去掉参数列表；如果有参数则去掉参数类型,只有一个参数的时候去掉参数括号
     * 4:如果接口函数的方法体只有一行代码，去掉花括号
     * 5:如果传入lambda表达式的那个方法的参数列表的只有一个lambda表达式，
     * 并且位于参数的最后一项，那么把lambda表达式提取到()外面
     * 6:执行到这里如果()没有任何内容了，那么可以把()去掉
     */
    fun lambdaDemo2() {
        Thread { Log.i("zui", "thread running 。。。。") }.start()
        Thread { Log.i("zui", "hahah") }.start()
    }

    /**
     * 如果 接口函数有一个参数的简化写法
     */
    fun lambdaButtom(context: Context) {
        val buttom = Button(context)
        buttom.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
            }
        })
    }

    /**
     * 当我们创建一个匿名对象的时候，能够按照如下步骤进行简化
     * var taskFun=object :View.OnClickListener{
    override fun onClick(v: View?) {
    }
    }
     * 1:去掉 object:View.OnClickListener,留下匿名对象的花括号
     * 2:去掉 override fun onClick.
     * 3:如果参数列表没有参数，去掉参数列表；如果有参数则去掉参数类型,只有一个参数的时候去掉参数括号
     * 4:进行如上散布后代码变成: { view->{} } 而且编译器报错..原因是因为根本不知道当前简化的lamda表达式是代表具体哪个接口函数，变量var task本身并不清楚是什么类型
     * 5:在花括号最外面加上具体的接口函数，变成 ：View.OnClickListener{view->{}}。这时编译器就不会报错了(看代码示例 taskFun2)。
     * 6:因为onClick()方法传入的只有一个函数，因此我们可以用 it 变量去代表该函数。代码变成var taskFun=View.OnClickListener { it.方法名 } 内部的it代表传入的View。
     */

    var taskFun = View.OnClickListener { }
    var taskFun2 = View.OnClickListener { view -> {} }

    /**
     *1:去掉 object :View.OnClickListener,留下匿名对象的花括号
     *2:去掉override fun onclick
     *3:如果参数列表没有参数，去掉参数列表；如果有参数则去掉参数类型,只有一个参数的时候去掉参数括号
     * 进行如上步骤后代码变成: { view->{} }
     *4:当接口函数只有一行代码的时候，去掉接口函数的方法体 花括号
     *5:如果传入lambda表达式的那个方法的参数列表的只有一个lambda表达式，
     * 并且位于参数的最后一项，那么把lambda表达式提取到()外面
     *6:执行到这里如果()没有任何内容了，那么可以把()去掉
     *7:执行到这里 基本上 简化成这样了 buttom.setOnClickListener { v->{logInstance("button click ")} }
     *最后对于只有一个参数的 lambda表达式，可以使用it代替其中的参数，那么就还可以简化
     * 去掉外层{}花括号 去掉 "v->"
     * 最后简化成:buttom.setOnClickListener { logInstance("button click ") }
     */
    fun lambdaButtom2(context: Context) {
        val buttom = Button(context)
        buttom.setOnClickListener { logInstance("button click ") }
    }

    fun lambdaArray3() {
        var arr = arrayOf(1, 2, 3, 4, 5, 6)
        Arrays.sort(arr, object : Comparator<Int> {
            override fun compare(o1: Int, o2: Int): Int {
                return o1 - o2
            }
        })

        Arrays.sort(arr) { o1, o2 -> o1 - o2 }
    }
}