package com.example.kotlin

import android.os.Build
import android.support.annotation.RequiresApi
import android.util.Log
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.javaConstructor
import kotlin.reflect.jvm.javaField

class reflectDemo {
    /**
     * 注意：目前kotlin的反射效率是比java反射效率慢很多的。Java反射的时间是微妙级别，kotlin反射的时间是毫米级别，
     * 目前两种在反射上的效率相差一个数量级。
     */
    /**
     * kotlin中，反射的对象是由两种类型的
     * 一种是kotlin自己的class对象，类型为 KClass
     * 一种是java的class对象，类型为Class
     * 两种用法差不多的，不过既然是用kotlin的话，那么就使用kotlin的KClass吧
     */
    fun reflectTest1() {
        /***********java的class对象获取********/
        //通过类全限定名 获取class对象
        val userReflectInstanc1 = Class.forName("com.example.kotlin.User")
        logInstance(userReflectInstanc1.toString())
        //通过类名获取class对象
        val userReflectInsanc2 = User::class.java
        logInstance(userReflectInsanc2.toString())
        //通过对象获取class实例
        val userReflectInstanc3 = User("6522", "teacher", "1554211568").javaClass
        logInstance(userReflectInstanc3.toString())

        /***********kotlin中的kclass对象***********/
        //获取kotlin的kClass对象的话 要添加kotlin的反射jar包
        val userReflectInstance4 = User::class
        logInstance(userReflectInstance4.toString())
    }

    /**
     * 获取class对象的属性
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun reflectTest2() {
        Log.i("zui", "reflectTest2")
        var userInstance = User("144551", "Genius", "xd554dsxxsd5")

        //java的反射获取属性，已经很熟悉了，现在只是简单演示一下，后面全部用kotlin的class对象进行反射kotlin的属性
        //通过类名获取class对象
        val userReflectInsanc2 = User::class.java
        logInstance(userReflectInsanc2.toString())
        val userIdField = userReflectInsanc2.getDeclaredField("userId")
        Log.i("zui", userIdField.toString());

        /**kotlin如何反射呢?反射kotlin的属性，就要用kotlin的class对象**/
        val userKTReflectInstanc = User::class
        //获取了当前kotlin的class对象的属性，该属性是包含了 该类中声明的属性以及父类中声明的所有属性集合。
        val memberProperties = userKTReflectInstanc.memberProperties
        //如果想要获取某个属性，必须遍历属性集合 然后找到我们需要的属性，然后就如同java的属性一样调用即可
        val userUniqueIdKtField =
            memberProperties.stream().filter { it.name == "userUniqueId" }.findAny().get().javaField
        userUniqueIdKtField?.isAccessible = true
        logInstance(userUniqueIdKtField?.get(userInstance).toString())
    }

    /**
     * 获取构造器
     */
    fun reflectTest3() {
        Log.i("zui", "reflectTest3")
        var userInstance = User("144551", "Genius", "xd554dsxxsd5")
        //获取kotlin形式的class对象
        val userKTReflectInstanc = User::class
        //获取user类所有的构造器
        val constructors = userKTReflectInstanc.constructors
        logInstance(constructors.toString())
        logInstance("*****获取主构造器****")
        //获取本类中主构造器
        val primaryConstructor = userKTReflectInstanc.primaryConstructor
        logInstance(primaryConstructor.toString())

        //kotlin中反射创建对象的要点(1)反射拿到kotlin形式的构造器对象(2)把kotlin形式的构造器对象转换成java的构造器
        // (3)通过构造器的newInstance(varary)创建对象

        //转换成Java的构造器
        val primaryJavaConstructor = primaryConstructor?.javaConstructor
        //通过构造器的newInstance(varary)创建对象
        val newInstance = primaryJavaConstructor?.newInstance("1342122", "shuimu", "d45e554sf")
        logInstance(newInstance.toString())
    }

    /**
     * 获取泛型的信息
     */
    fun reflectTest4() {
        var userKtReflectInstance = User::class
        //获取该类中所有的泛型信息
        val typeParameters = userKtReflectInstance.typeParameters
        typeParameters.apply {
            //是否被Reifide关键字修饰
            this[0].isReified
            //泛型的名称
            this[0].name
            //该泛型的上限 是什么类型
            this[0].upperBounds
        }
    }
}

