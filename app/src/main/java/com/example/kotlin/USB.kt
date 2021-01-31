package com.example.kotlin

import android.util.Log

/**
 * 在java中接口是不可以定义属性的，只可以定义常量。
 * 在kotlin中接口可以定义属性，以及明确属性的类型，但是不可以给属性赋值，可以定义方法 或者抽象方法，
 * 接口的方法可以有默认的实现(其实就是定义普通方法了)。
 * 定义的方法与java定义的方法差不多。在kotlin中认为 接口中定义的是一个规范，因此
 * 接口中的属性 只可以定义属性名 以及属性类型 不可以定义具体的属性值；方法的话 参考java即可

kotlin接口中定义的普通方法,在编译成java代码时,是这样的：
interface USB{
public static String compomnetName="";
void run();
void showOff();

public static class DefaultImpls {
public static void showOff(USB $this){
String var1 = "i'm Clickable!";
System.out.println(var1);
}
}
}
所有USB的子类,在默认方法中，kotlin都会自动去生成 showOff()方法的实现,并且自动写上调用 USB.DefaultImpls.showOff(this)。方法
这就是 kotlin接口实现默认方法的方式。
由此也能明白,java要是实现kotlin的接口必须重写接口中所有的方法。

在java中调用父类的方法,一般是 super.xxx()；而在kotlin中 super<父类类型>.XXX()；
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

    /**
     * 定义普通方法.
     */
    fun showOff() {
        val var1 = "i'm Clickable!"
        println(var1)
    }
}

/**
 * 在kotlin中默认所有的类都是 final修饰的，不可被继承的，方法也是final修饰的，不可被重写，
 * 如果你想某个类可以被继承，某方法可以被重写,就需要在类前面或 方法前面添加 open关键字。
 *
 * 如果子类A继承了父类之后，重写了父类的方法,这时候 子类A又被其他的子类B继承,如果子类A不想被自己所重写的某个父类方法绩效被下一级的子类B重写，
 * 那么就必须在重写的 override关键字前面 加上final 关键字(这时 该方法 就是这样的 : final override fun XXX():返回值类型{函数体})。
 */
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

    /**
     * 不给下一级的子类重写
     */
    final override fun showOff() {
        super.showOff()
        logInstance("")
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

    fun startRunning() {
        for (item in USBList) {
            item.run()
        }
    }
}