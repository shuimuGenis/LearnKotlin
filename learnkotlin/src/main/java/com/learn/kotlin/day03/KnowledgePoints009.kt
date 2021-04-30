package com.learn.kotlin.day03

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc
 *
 * kotlin 对象表达式
 * kotlin中的对象表达式 相当于java中的匿名内部类，即有java的匿名内部类的基本功能，也解决了Java匿名内部类的缺陷
 * 声明对象表达式的格式：
 * [val/var] 变量名= object:[类,接口,类,接口]{
 *     init{}；//可省略
 *     声明属性；
 *     声明方法；
 * }
 * 使用方法:--> 变量名.声明属性/变量名.声明方法([有参数则传参数])
 *
 * java的匿名内部类特点
 * (1)匿名内部类是没有名字的类。(2)匿名内部类一定是"实现了某个接口或继承了某个类"(3)java在运行时是理所当然的把【匿名内部类当作成是它所实现的接口或继承的类】来看待的
 * 而kotlin中通过对象表达式实现了java的匿名内部类的同时，对匿名内部类 的(2)(3)点进行了修正。
 * 演示kotlin匿名内部类的第一个特点：没有类名
 *
 *interface MyInterface {
 *    fun myPrint(i: Int)
 *}

 *fun test() {
 *    val temp01 = object : MyInterface {
 *        override fun myPrint(i: Int) {
 *              print("this is logic of method for MyInterfase")
 *        }
 *    }
 *}
 *上面的示例就算 对象表达式的基本操作,跟声明一个java匿名内部类基本一致,只是关键字变了而已。
 *下来演示匿名内部类的对(2)的修正,
 * java的匿名内部类是【一定是实现了某个接口或者继承了某个类,还只能是一个,要么是实现接口,要么是继承类。该结论经过代码测试,确实如此】;
 * kotlin的匿名内部类规则1：【你想继承类就继承类,你想实现接口就实现接口,不限个数,继承类和实现接口可以同时进行】
 * kotlin的匿名内部类规则2:【当你既不想实现接口也不想继承类时,可以不实现接口也不继承类的方式去声明一个匿名对象,但建议是在局部范围声明或者作为被private修饰的成员变量】
 *规则1的示例:定义了接口 MyInterface,定义了抽象类Myabstract,则匿名内部类能即实现接口又继承抽象类。
 * val tempInnerObject = object : MyInterface, Myabstract() {..对应实现方法..}
 *规则2的示例：不想实现接口也不想继承类 的方式去声明一个匿名对象
 *val tempInnerObject =object{定义方法;声明属性;}；
 *
 * kotlin的对(3)的修正是 [对象表达式作为"局部范围内(一般指方法内)声明的匿名对象"和作为"被private修饰成员变量"时,能够被识别为“真正的类型”,而不是被识别成其所实现的接口或其所继承的类]
 * 在java中的匿名内部类只会被当作其所实现的接口或者其所继承的类来看待，在匿名内部类中所存在的方法,如果其父类(实现或继承的接口/抽象类)中没有该方法的话,则是不能被外部调用的(即不能被 对象.方法名的方式调用)。
 * 而kotlin中的匿名内部类会更灵活一点,如果kotlin中匿名内部类是局部范围内声明的或private修饰的,那么它就会被当作一个新类,不仅其父类的方法能够外部调用,匿名内部类中新声明的方法也能被外部调用。
 * 如果kotlin中的匿名内部类[不是局部范围内声明的或private修饰的]，那么效果就跟java的匿名内部类一致。
 *
 * 注意:当通过"不实现接口也不继承类 的方式去声明匿名对象"时,匿名对象的父类将会是Any类型的。
 * 这时的匿名内部类,根据这个是否满足这个条件:[局部范围内声明的或private修饰的]存在不同的类型,满足则是匿名内部类的类型,不满足则是父类的类型。
 */
class KnowledgePoints009 {

    fun test() {
        val temp01 = object : MyInterface {
            override fun myPrint(i: Int) {
                print("this is logic of method for MyInterfase")
            }

            fun ownselt() {
                print("this is ownner method")
            }
        }
        temp01.myPrint(0)
        temp01.ownselt()
        val tempInnerObject = object : MyInterface, Myabstract() {
            init {
                print("初始化代码块执行")
            }

            override fun myPrint(i: Int) {
                print("this is logic of method for MyInterfase")
            }

            override fun getGbs(): String {
                TODO("Not yet implemented")
            }
        }
    }
}

interface MyInterface {
    fun myPrint(i: Int)
}

abstract class Myabstract {
    abstract fun getGbs(): String
}
