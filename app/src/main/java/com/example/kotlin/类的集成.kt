package com.example.kotlin

import android.view.View

/**
 * 在kotlin中定义抽象类 是使用abstract修饰符。
 * 抽象类可以定义属性，可以给属性赋值，可以义具体定的方法，也可以定义抽象方法
抽象类中的方法 默认是可以被子类重写的。

 * 默认每一个类(普通类或者抽象类)都有一个无参构造器，抽象类也是默认是无参构造器。
 * 子类在定义时候要声明 覆盖父类至少一个构造器
 */
abstract class PersonRelate1

/**
 * 因为父类是默认无参构造器，因此子类在定义时要覆盖这个无参构造器
 */
class SonRelate : PersonRelate1()

/**
 * 构造方法中的匿名函数/lambda表达是想要在类中任意地方使用的话
 * 匿名函数/lambda表达式就必须是成员变量
 */
abstract class PersonRelate2(name: String, age: Int) {
    abstract fun run()
    open val subejct: String = "km"
    //    val textContent :String? 抽象类中 定义的属性 必须赋值
    fun rung() {

    }

}

/**
 * 把匿名函数/lambda表达式定义为成员变量,通过构造器传入。当然也可以通过方法进行赋值
 */
class CoroutineData {
    private var name: String = ""
    private var block: (view: View?) -> String = { "" }

    constructor(name: String, block: (view: View?) -> String) {
        this.name = name
        this.block = block
    }

    fun execute() = block(null)

    fun setBlock(replace: (view: View?) -> String) {
        block = replace
    }
}


/**
 * 因为父类是有两个构造器，因此之类定义的时候可以选择 覆盖其中一个，这里选择覆盖 两个参数的构造器
 */
class SonRelate2(name: String, age: Int) : PersonRelate2(name, age) {
    override val subejct: String = "dd"
    override fun run() {
    }
}

class SonRelate3 : PersonRelate2 {
    constructor(name: String, age: Int) : super(name, age)

    override fun run() {
    }
}

/**
 * 在kotlin中普通类和抽象类在继承方面是不同的。
 * kotlin中抽象类默认是可以继承的，但是 普通类默认是不可以继承的
 * 声明一个普通的类的时候 默认是使用final修饰这个类的 例如class sonRelate()
 * 这个sonRelate类就是final修饰的类 是不可以被继承的。
 * 想要一个普通类能够被继承，需要在类前面使用open关键字
 *
 * 在kotlin中 抽象类的方法是能够被子类重写的。普通类的方法默认是不可以被子类重写的，对于属性而言，不管
 * 是抽象类还是普通类 默认都是不能被子类重写的，在kotlin中凡是不能被重写的方法或属性，就用open关键字修饰它就 可以重写了。
 * */
open class PersonRelate3 {
    open var name: String? = null

    open fun textOverrideFun() {

    }
}

class SonRealte3 : PersonRelate3() {
    override var name: String? = "3"
    override fun textOverrideFun() {
        super.textOverrideFun()
    }
}
