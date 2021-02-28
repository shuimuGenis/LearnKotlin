package com.example.kotlin.day06

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin.R
import com.example.kotlin.logInstance

class TestDemoDay6Actiity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_6)
        aboutHeightFunctionInfo()
        aboutAnnotationDemo()
    }

    /**
     * 关于高阶函数的一些特性的补充测试
     */
    fun aboutHeightFunctionInfo() {
        val textDemo06Instance = TextDemo06()
        val addFunction = textDemo06Instance.add(3)
        //打印的结果分别是 13 ， 8
        logInstance("测试高阶函数返回的匿名函数多次调用结果： ${addFunction(10)}，${addFunction(5)}")
        //测试函数内的匿名函数对外部函数的状态持有的观察
        val modifyFunction = textDemo06Instance.modify(Goods("shui", 20))
        //打印的结果分别是 Goods(name=shui, price=22) , Goods(name=shui, price=26)}
        logInstance("测试函数内的匿名函数对外部函数的状态持有的观察：${modifyFunction(2)} , ${modifyFunction(4)}}")
        /**
         * 由上面的结果可以看出，匿名函数持有外部函数声明的变量以及状态的副本。
         * 当变量是基本数据类型的时候，匿名函数持有原始数据的副本。原始基本数据的值不会发生改变
         * 当变量是引用类型的时候，匿名函数持有的是该引用的地址，引用的地址是一个数值来的，是不会改变的，所以匿名函数持有的仍然是一个这个数值的副本，
         * 只不过该数值指向一个对象而已。匿名函数对该地址值所指向的对象的所有操作都会有效，都会体现在对象身上。
         */
    }

    /**
     * 关于注解的 实例
     */
    fun aboutAnnotationDemo() {
        //获取到 java的class对象
        val useriNfoClazz = QQSoftWare::class.java
        //判断注解 XXX类型的注解 是否存在
        val annotationPresent = useriNfoClazz.isAnnotationPresent(UserInfoAnno::class.java)
        if (annotationPresent) {
            //获取到值定的注解对象
            val annotation = useriNfoClazz.getAnnotation(UserInfoAnno::class.java)
            annotation?.apply {
                if (this.isVip) {
                    logInstance("不是 VIP")
                    return
                }

                if (this.level !in 1..3) {
                    logInstance("账户的等级异常")
                    return
                }
                logInstance("用户名 ${this.name} isVip ${this.isVip} level ${this.level}")
            }
        }
    }

}