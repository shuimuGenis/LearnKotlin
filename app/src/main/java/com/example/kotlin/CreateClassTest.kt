@file:JvmName("CreateClass")

package com.example.kotlin

import android.util.Log

/**
 * 闭包是函数的一种运行环境，持有函数的运行状态。函数内部可以定义函数，也可以定义类。
 * 闭包的直接体现就是，用在高级函数中。
 * 什么是高阶函数，就是 函数的参数/返回值是一个匿名函数的函数。
 * 高阶函数中的匿名函数，持有该高阶函数的状态(包括但不限于持有函数的参数，函数声明的对象，声明的变量等)，这就是闭包。
 */

/**
 * 定义在文件中的函数是顶层函数(全局函数)，定义在类中的函数是普通的类函数
 * 顶层函数默认是public static。因此任何地方都可以使用它
 * 顶层函数用来替代java中的那些存放了 很多静态方法的工具类。
 * kotlin中顶层函数调用只需要导入该函数的包名就可以使用了,java中对于kotlin的顶层函数是怎样调用的呢？kotlin的顶层函数在jvm中实际上会自动编译生成一个类，
 * 类名为"kotlin文件名+kt",所以在java中调用顶层函数就是  "kotlin文件名+kt".函数名(参数列表) 的方式调用。顶级属性同理。
 *
 * 因为对于kotlin的顶级函数，编译器会自动生成"kotlin文件名+kt" 的类,所以当我们修改类名的时候,编译器就重新生成对应的 "kotlin文件名+kt" 类,那么如何固定编译器生成的对应的
 * "kotlin文件名+kt" 类呢?... 在该文件的第一行添加这样的写法： @file:JvmName("你想要的类名")  这样 就可以固定编译器生成的类名了。
 */
//topFun方法就是一个顶层函数，定义在文件中，而不是类中
fun topFun() {}

//当你的类没有任何内容的时候，你可以这样定义一个类。:class UserInfo
//在kotlin中文件的名字不一定要跟类名一致。完全不同都是可以的。
class UserInfo {
    //kotlin中声明的变量/属性等 默认都是要赋值的。
    //类中默认声明的属性 都会自定添加setter/getting方法,如果我们想要重写这些方法怎么办呢?
    var name: String = ""
    var age: Int = 0
    var level: Int = 0

    //重写属性的setter/getter方法
    var groupName: String = "无"
        get() {
            Log.i("zui", "重写getter方法 $field")
            return field;
        }
        set(value) {
            field = value;
            Log.i("zui", "重写的setter方法$field")
        }
}

/*在kotlin中类的构造器分为主构造器 和次构造器，并且次构造器必须调用主构造器，
* 声明在类上面的叫做 主构造器 声明在类 内部的叫做次构造器。init代码块是主构造器的方法体
* 主构造器上声明的参数 会在类的内部自动生成对应的属性以及其getter/setter方法
* */
class Person constructor(val name: String, val age: Int) {
    init {
        Log.i("zui", "主构造器 init代码块 $name ---$age")
    }

    //val 和var都不能修饰 次构造器中的参数。
    constructor(name: String) : this(name, 18)

    constructor(name: String, gentel: String) : this(name, 18)

    //kotilin中定义方法的基本格式: fun 函数名(参数列表):返回值类型{方法体}
    /* fun isChengNianRen(): Boolean {
         return age >= 18;
     }*/

    //如果你定义的方法 有返回值 且 只有一行代码 那么可以这样写
    //步骤先省略大括号 变成这样 fun isChengNianRen(): Boolean =age>=18
    //因为kotlin能自动推导类型 能肯定的推导出返回的是Boolean值，所以最终简化成下面这样
    fun isChengNianRen() = age >= 18

    /*这里有两个知识点该类型可能为null
    (1)在类型后面加"?"表示,
    (2)在声明类的属性时,不管声明的是什么类型的属性,都必须赋予初始值。
    * */
    var a: String? = null//可空类型可以手动赋值null

    /*
     在方法的返回值类型也可以是可空类型。表示该方法有可能返回空
     */
    fun testNullFf(): String? {
        return null
    }

    /*我们知道类的成员变量在定义的时候，就要初始化，赋予默认值，但是我们希望"属性在未来某一时刻初始化，而不是现在"
    这时候就应该使用 lateinit var 关键字了。但是注意 这种需求只能在对象类型的属性使用
    对于kotlin中的基本数据类型 默认是定义的时候要初始化的，我们也可以进行懒加载初始化，就是用到这个属性的时候才
    初始化。
     */
    /*lateinit是用来修饰var的变量的。lazy代码块是用来修饰val的变量的*/
    /*
    lateinit var 表示在未来的某个时间进行初始化,延迟初始化这个属性。这个就需要我们程序员自己去控制了。uniqueId没有被初始化就调用
    这是后悔抛出: late init property uniqueId is no inited;
     */
    lateinit var uniqueId: String

    /*
     *在kotlin中我们一般使用 伴生对象的方式  来定义 静态变量和静态方法。
     */
    companion object {
        var GROUP_NAME: String = "groupName"
    }

}

data class User(val userId: String, val serType: String, val userUniqueId: String)

/*
    *在kotlin中 我们想定义单利类应该怎么定义 呢？
    *其实很简单啊，定义普通的类用class 类名的方式定义
    * 定义单利类，则使用 object 类名 的方式定义。
    */
object VideoManage {
    var videoName: String = "电影"
}

/*
在kotlin中 创建的内部类默认都是静态内部类，当前面有inner关键字修饰的时候，才是非静态内部类
* */

class OutTestClass {
    /*
     * 这样就定义了一个静态内部类，前面已经说过，当类的内部什么代码都没有的时候，可以省略花括号。
     */
    class innerTestClass

    var aOutClass = "你好"

    /*
     *这样就定义了一个 非静态内部类。
     * 问题:在内部类中 我们怎么访问外部类的属性呢？
       可以通过使用 ： this@外部类名.属性 /this@外部类名.方法 的形式 去访问外部类的成员
     */
    inner class innerTest {
        fun textFun() {
            this@OutTestClass.aOutClass
        }
    }


}