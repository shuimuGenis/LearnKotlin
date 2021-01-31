package com.example.kotlin

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Log.i
import android.view.View
import androidx.annotation.RequiresApi
import com.example.java2kotlin.SAmTest
import com.example.kotlin.coroutine.CoroutineActivity
import com.example.kotlin.day06.TestDemoDay6Actiity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : Activity(), View.OnClickListener {
    override fun onClick(p0: View?) {
        var intent: Intent? = null
        p0?.apply {
            if (R.id.go_to_day06 == id) {
                intent = Intent(this@MainActivity, TestDemoDay6Actiity::class.java)
            } else if (R.id.Go2StudyCoroutine == id) {
                intent = Intent(this@MainActivity, CoroutineActivity::class.java)
            }
        }
        intent?.also{ startActivity(intent) }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        go_to_day06.setOnClickListener(this)
        Go2StudyCoroutine.setOnClickListener(this)
        i("zui", "hello world ")
        /*kotlin 中定义变量格式 var 变量名:变量类型 = 变量值。
        * Kotlin中变量默认是不可以为null的，默认是必须赋值的，但是我们可以通过"?"符号类设置能够为null的变量
        * 定义可以赋null值的变量的格式:var 变量名:变量类型? = null；
        * */
        var aBoolean: Boolean = true
        var aOtherBoolean = false
        var aThreeBoolean: Boolean?//当一个变量位可空类型的时候，变量才可以赋予null值

        var bDouble: Double = 1.223
        //Double.Min_VAlUe是一个整数,即(最小负数的绝对值)我们可以用 -Double.Max_VAlue表示的
        var aDouble = Double.MIN_VALUE

        /*在java中 小范围的数据类型是可以直接赋值给大范围的数据类型的，因为java会在这过程中进行隐式转换。
        * 但是在kotlin中是不会进行隐式转换的，所以在kotlin中直接把小范围的数 赋值给大范围 是会报错的
        * 例如 ：下面这两行代码是会报错的
        *   var textNum=10;
            var textNumA:Double = textNum;*/
        //正确的写法是
        var textNum = 10
        var textNumA: Double = textNum.toDouble()

        /*在kotlin的字符串中 “$”美元符号有特殊的含义，表示 取出某个对象的值。
        * 因此在kotlin的字符串拼接中 "$"美元符号有非常方便的效果，例如数据库查询的时候。
        * 格式: $某个对象 或者 ${某个对象}*/
        var aStr = 3
        var bStr = 4
        i("zui", "${aStr} + $bStr = ${aStr + bStr}")

        /*
         *在kotlin中所有的都是对象，kotlin的基本数据类型都相当于是java的基本数据的包装类型，都是对象。
          在kotlin中如何创建一个数组对象呢?
          kotlin中创建数组对象的格式:
          var 数组变量名:Array<数组类型> = arrayOf(vararg elements:String)
          vararg关键字表示 可变参数的  elements将会是最后 可变参数所生产的数组的变量引用。
         */
        var strArr: Array<String> = arrayOf("你", "真的", "很可爱啊", "天使")
        /*
         *上面是 最简单的数组创建方式，也是常用的一种。不过对于kotlin内的基本数据类型，也封装了对于的数组。
         *所有的基本数据类型 的数组 其实都是对 Array类型进行的封装而已。
         */
        var intArr: IntArray = intArrayOf(1, 1, 2, 3, 4)
        /*kotlin的数组虽然没有toString()方法，当时提供了joinToString("分隔符")方法来转换陈字符串。
          数组的取值有两种方式:一种是调用get(index)方法 一种是这种写法：[index]
         */
        Log.i("zui", intArr.joinToString { "," })
        Log.i("zui", intArr.get(0).toString())
        Log.i("zui", strArr[0])//这种写法 其实就是调用get(index)函数而已

        /*
         *kotlin中遍历数组
         */
        for (item in strArr) {
            Log.i("zui", item);
        }
        /*
          如果需要在遍历的同时获取kotlin的索引的话
         */
        for ((index, item) in strArr.withIndex()) {
            Log.i("zui", "$index ---- $item")
        }
        /*关于Range的操作。Range可以帮我们实现一个范围，操作跟Array差不多
         */
        val range1 = 0..10//声明了一个[0,10]的区间。
        for (item in range1) {
            Log.i("zui", "range $item")
        }
        val range2 = 0 until 10 //声明了一个[0,10)的区间，注意 不包括10
        for (item in range2) {
            Log.i("zui", "range until $item")
        }
        val range3 = 10 downTo 0//声明了一个[10,0]的区间。
        for (item in range3) {
            Log.i("zui", "range down $item")
        }
        val range4 = 0 until 10 step 2//声明了一个区间,区间从0开始 每次步长为2，一直到大于等于10
        for (item in range4) {
            Log.i("zui", "range step 2 $item")
        }
        //打印为 0 3 6 9 所以结论是 声明了一个区间,区间从0开始 每次步长为3，一直到不大于10为止
        val range5 = 0 until 10 step 3
        for (item in range5) {
            Log.i("zui", "range step3 $item")
        }
        ///
        Log.i("zui", "*******kotlin的面相对象******")
        //kotlin中 创建对象是不需要new关键字的了。
        // 所以创建对象可这么写:var userInfo:UserInfo = UserInfo()
        //同时kotlin能够自定推到变量的类型，因此又可以这样写
        var userInfo = UserInfo()//这里就是进行了自动推导类型。

        var person = Person("水木", "男")
        Log.i("zui", "name：${person.name}")
        Log.i("zui", "name：${person.isChengNianRen()}")

        /*
        即使调用的方法返回的是可空类型，我们仍然可以使用val修饰的变量 来接受它
        但是 后面调用的时候 要进行空判断 否则编译不通过
         */
        val theFunctionIsnull = person.testNullFf()
        /*表示如果theFunctionIsnull不为null这调用 返回不调用。这里的theFunctionIsnull必须加上"?"
        * 否则编译报错。因为theFunctionIsnull变量接受的类型是可空类型,可空类型在调用任何方法‘属性都必须先
        * 进行非空判断
        **/
        theFunctionIsnull?.length
        /*如果你确定某个可空类型肯定会被赋值，不会为空,那么可以使用"!!"符号
        * 这个符号表示如果不为空 则调用方法或属性，如果为空 抛出异常*/
//        theFunctionIsnull!!.length//这里可以看到 编译正常通过。
        /*
            匿名函数的调用
         */
        /*
        匿名函数的最原始的调用方式就是调用匿名函数的invoke()方法
         */
        val result1 = add.invoke(2, 3)
        Log.i("zui", "匿名函数的invoke方式：$result1")
        /*因为invoke方法是一个操作符，通过这个操作符 我们可以把写法转换成下面这样add(参数列表)
         */
        val result2 = add(5, 6)
        Log.i("zui", "匿名函数的invoke方式：$result2")

        /*
         前两个add()：是把add函数的结果作为一个参数，因为返回的结果是Int所以可以作为参数传入
         最后一个add：是把整个方法作为参数传入。
         */

        val compute1 = compute(add(2, 30), add(2, 3), decreate)
        val compute2 = compute(add(2, 30), add(2, 3), add)
        Log.i("zui", "   compute(add(2,30),add(2,3),decreate) :$compute1")
        Log.i("zui", "   compute(add(2,30),add(2,3),add) :$compute2")

        var operatorcreate = CreateOperate()
        operatorcreate + 20
        operatorcreate lalala "aaajj"
        Log.i("zui", operatorcreate say "Kotlin")

        //单利类使用 很简单 直接用类名即可。因为构造方法是是私有化的
        var singleInstance = VideoManage


        //静态内部类的初始化
        var staticInnerClassInstance = OutTestClass.innerTestClass()
        //非静态内部类的初始化
        var notstaticInnerClassInstance = OutTestClass().innerTest()

        //进行了类扩展之后 该类的所有对象都可以调用这些扩展方法
        val result = "hello".cxIsNotEmpty()
        Log.i("zui", "测试类的扩展方法 : $result")
        Log.i("zui", "测试接口的知识:--------------")

        val mouseUSB = mouse()
        val keyBoardUSB = keyboard()
        var computer = computer()
        computer.addUSB(mouseUSB)
        computer.addUSB(keyBoardUSB)
        computer.startRunning()

        //测试集合
        val collectioOperation = CollectionOperationTest()
        collectioOperation.operationTest1()

        val notNameTest = lambdaTest()
        val notNmaeresult = notNameTest.computeFun(10, 10, notNameTest.notNameFun)

        notNameTest.testLambdaA()

        Log.i("zui", "notNameTest : $notNmaeresult")


        //测试高阶函数
        val heighFun = HeightLevelFun()
        heighFun.heightDemo7()
        heighFun.heightDemo8()

        val customHeightFun = CustomHeightFun()
        //这里就从高阶函数中获取了一个匿名函数作为返回值，并且用一个变量去引用匿名函数
        val countAddHfun = customHeightFun.countAddHfun()
        //接下来 就是正常的调用匿名函数了。
        // 调用方式:(1:引用匿名函数的变量.invoke() (2:引用匿名函数的变量()
        countAddHfun.invoke()
        //这里从高阶函数中获取了一个有返回值的匿名函数。
        val computeIncreateHfun = customHeightFun.computeIncreateHfun(20)
        //调用返回的匿名函数打印数值
        Log.i("zui", "computeIncreateHfun() 的 值 ${computeIncreateHfun(5)}")

        /**
         * 高阶复合函数的调用。
         */
        val compleFixFun = add5 compleFix plus3
        Log.i("zui", "${compleFixFun(20)}")

        /**
         * 科里化的测试
         */
        cerryTest("zui")("测试科里化")
        logInstance("现在 打印log 只需要关注 内容部分即可")
        //测试 kotlin 调用 java 的 接口函数 。
//        SAmTest().SAMTest1()
        SAmTest().SAMTest2()

//        Thread { IOStreamDemo().IOTest1() }.start()
        reflectDemo().reflectTest1()
        reflectDemo().reflectTest2()
        reflectDemo().reflectTest3()
    }

    /*匿名函数，顾名思义就是没有方法名的函数，
     普通方法的定义是:fun 方法名(参数列表):返回值类型{代码块}
     匿名函数就是把方法名去掉就行了。
     那么问题来了，匿名函数没有方法名那我们怎么调用呢?
     在kotlin中通常是通过把匿名函数当做一个对象赋值给一个变量(val/var修饰的都行)，通过变量来引用这个函数，
     那么有了变量引用匿名函数，这时应该怎么用呢?用法是：变量名.invoke(参数列表)
     invoke方法一个操作符，它是可以省略的，所以用法省略之后是: 变量名(参数列表)
     匿名函数,既用变量引用一个函数，这种方式下函数可以当成一个变量传入另一个函数里进行执行，
     匿名函数 与闭包block代码块配合使用就能达成:一个函数传入另一个函数中使用的功能啦
      */
    var add = fun(num1: Int, num2: Int): Int {
        return num1 + num2
    }

    var decreate = fun(num1: Int, num2: Int) = num1 - num2
    /*
    想要匿名函数当做一个参数传入另一个函数里面，那么首先另一个函数在定义的时候，在参数列表里就必须定义了，它可以传入一个函数作为参数，
    否则是不可以的,就变成只是把函数的结果作为参数传入了、
     */
    fun compute(num1: Int, num2: Int, block: (Int, Int) -> Int): Int {
        return block(num1, num2)
    }
}


