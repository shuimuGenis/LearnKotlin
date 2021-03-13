package com.learn.kotlin.day01

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc
 */

/**
 * 学习解构声明语法
 */
fun testDemo02() {
    /**
     * Book类是 data class关键字修饰的数据类....数据类之所以能够支持解构声明的语法是因为
     * 在编译的时候会 按照 类中声明的属性的顺序,依次创建component1(),component2(),component3()....componentN()的方法来获取属性；
     * N个属性就创建1~N命名的component1()~componentN()方法。注意点1：这些 componentN()方法都是 operator 关键字修饰的。
     */
    val tempUser = Book("57791", "5", "123456789887451")
    //这里的写法,因为user是数据类，在编译成java文件的时候,实际上调用的是componentN()方法
    val temUserId = tempUser.name
    val tempUserType = tempUser.price
    val tempUserUniqueId = tempUser.produtor
    //上面的写法可以等价于，这样
    val userid = tempUser.component1()
    val serType = tempUser.component2()
    val userUniqueId = tempUser.component3()
    /*
    * 我们知道 data class 修饰的数据类 能够支持解构声明语法是因为 编译的时候,默认会生成 operator 修饰的 componentN()方法
    * 对于一个普通的类，当然是肯定不支持解构声明的语法的,普通类 kotlin在编译的时候不会生成 operator 修饰的 componentN()方法。
    * 那么问题就来了，如果我们给一个 普通类 ,我们自己去加上 operator 修饰的 componentN()方法的话，是不是就能够支持解构声明语法了呢？
    * 对的!。 这时候支持了。
    * */

    /**
     * 一个普通类支持了解构声明的语法
     */
    val (tName, tPrice) = TestDemo9()

    /**
     * kotlin中的集合是支持解构声明语法的，原来也是一致的
     */
    //数组
    val array = arrayOf(1, 2, 3)
    var (a1, a2, a3) = array
    //集合
    val list = listOf("1", "2", "3")
    val (s1, s2, s3) = list
    //map
    val map = mapOf("key1" to 1, "key2" to 2, "key3" to 3)
    for ((key, value) in map) {
        println("$key-$value")
    }
}

/**
 * 对于一个普通的类，当然是肯定不支持解构声明的语法的,普通类 kotlin在编译的时候不会生成 operator 修饰的 componentN()方法。
 * 那么问题就来了，如果我们给一个 普通类 ,我们自己去加上 operator 修饰的 componentN()方法的话，是不是就能够支持解构声明语法了呢？
 * 对的!。 这时候支持了。
 */
class TestDemo9 {
    /**声明属性的顺序*/
    var name: String = "解构"
    var price: Int = 10

    /**按照声明的属性顺序 依次代表对应的属性获取方法*/
    operator fun component1(): String {
        return name;
    }

    /**按照声明的属性顺序 依次代表对应的属性获取方法*/
    operator fun component2(): Int {
        return price
    }
}

data class Book(val name: String = "库洛牌大全", val price: String = "一亿", val produtor: String = "库洛里多")