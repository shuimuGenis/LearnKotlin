package com.example.kotlin.day06

import com.example.kotlin.logInstance
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 在kotlin中，委托的概念有两种(1)类委托(2)属性委托
 * 类委托就是java中的委托模式/装饰设计模式。以前用java要写一堆委托模式必须的规范代码，但是kotlin可以直接通过by关键字直接支持委托模式。
 */
/**类委托示例----start*/
interface Base {
    fun doBaseAction()
}

class BaseImpl : Base {

    override fun doBaseAction() {
        logInstance("我是 Base的具体实现方法")
    }
}

/**类委托出现了。把对于Base接口方法的具体实现，委托给传入的tempB对象。
 * */
class BaseProxy(tempB: Base) : Base by tempB {
    fun doProxy() {
        logInstance("自己的方法")
    }
}

class TestBaseDemo {
    fun testAction() {
        val baseImpl = BaseImpl()
        //BaseProxy中对于Base接口方法的实现，委托给了BaseImpl对象，同时BaseProxy类自己可以有接口之外的方法。
        BaseProxy(baseImpl).doBaseAction()
    }

    fun testPropertyAction() {
        val propertyPerson = PropertyPerson()
        propertyPerson.name = "Genius"
        logInstance(propertyPerson.name)
        propertyPerson.name = "Spring"
        logInstance(propertyPerson.name)
    }

    fun testLazyAction() {
        logInstance("测试lazy函数：${BookLibrary().book}")
    }
}
/**  类委托示例-----end*/

/**kotlin的委托属性是指，类的属性不在类中去具体的定义，而是委托给一个对象去统一管理
 * 下面的示例中，PropertyPerson的name属性委托给了PersonNameDelegate去管理
 * */
class PropertyPerson {
    var name: String by PersonNameDelegate2()
}

/**
 * 属性委托的对象只要有 getValue(thisReft: Any, property: KProperty<*>)/setValue(thisReft: Any, property: KProperty<*>, value: String)方法即可，不需要实现任何接口
 * 属性委托的对象的getValue/setValue方法必须是示例中固定的格式，要不然编译器报错。
 * kotlin为了预防写的方法不正确，特地提供了 ReadWriteProperty<T,R>接口，预防我们不会写固定格式的方法。
 */
class PersonNameDelegate {
    var tempName: String = ""
    operator fun getValue(thisReft: Any, property: KProperty<*>): String {
        return tempName
    }

    operator fun setValue(thisReft: Any, property: KProperty<*>, value: String) {
        tempName = value
    }
}

class PersonNameDelegate2 : ReadWriteProperty<Any, String> {
    var tempName: String = ""
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return tempName
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        tempName = value
    }
}

/**
 * kotlin 中 提供了Delegates工具类。内部都是实现了ReadWriteProperty的子类。
 * Delegates是object关键字修饰的，说明它是一个单例类。
 */

class Product {
    /*
    Delegates.notNull()，实际上返回的是一个NotNullVar的对象，NotNullVar是ReadWritePropert的子类
    NotNullVar的getValue方法中会判断当前的属性是否有初始值，如果没有则抛出异常，有则返回数值。
    * */
    var name: String by Delegates.notNull()
    /**
     * Delegates.observable()方法实际上返回一个ObservableProperty对象，该对象是ReadWritePropert的子类。
     * 内部的具体实现是很简单的，看源码即明白
     */
    var type: String by Delegates.observable("Android") { property, oldValue, newValue ->
        logInstance(
            "oldValue = $oldValue; newValue=$newValue"
        )
    }
}


/**在kotlin的委托中还有一种特殊的委托，lazy委托---》 数据只在第一次被访问的时候被初始化，无法初始化第二次，因此只能与val修饰的属性配合使用。
 * lazy()是一个全局方法，参数需要传入一个匿名函数() -> T。
 * lazy函数是系统定义的函数，只允许初始化一次，因此 必须是val修饰
 * 注意:lateinit 修饰var  lazy函数 修饰val...两者都是延迟初始化属性。
 * */
data class Book(var name: String, var prive: Double)

class BookLibrary {
    val book: Book by lazy { Book("kotlin 从入门到泛起", 120.0) }
}


