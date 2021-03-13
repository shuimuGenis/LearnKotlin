/**
 *kotlin 中的控制语句
 * if语句
 * 在kotlin中 if语句是被看做一个表达式存在的
 * 用法一:与传统的java相同的写法
 * var num:Int =2
 * if (num==2){print("num==$num")}else {print("num 大于2")}
 * 用法二：作为三元运算符存在
 * val result=if(num>3) 2 else -1
 * 用法三：作为一个块 存在
 * val result=if(条件1){
 *      代码1
 *      代码2
 *      ...最后一行的结果作为条件1的返回值
 * }else if(条件2){
 *      代码1
 *      代码2
 *      ...最后一行的结果作为条件2的返回值
 * }else if(条件3){
 *      代码1
 *      代码2
 *      ...最后一行的结果作为条件3的返回值
 * }
 *
 *kotlin中的when语句
 * kotlin移除了switch语法,使用when进行替代,并且when比switch更强大
 * 用法一:基础用法,实现switch功能
 * when(5){
 *   1 ->{print("1")}
 *   2->{代码}
 *   3->{代码}
 *   4->{代码}
 *   else->{代码}
 * }
 * 用法二:实现switch的break功能。即当满足1,2,3条件时都执行代码1的逻辑，满足4,5条件都执行代码2的逻辑
 * when(5){
 *   1,2,3->{代码1}
 *   4,5->{代码2}
 * }
 * 用法三：通过(in 在/ !in 不在)关键字检查值是否在集合或者数组中国
 * var arrayList=arrayOf(1,2,3,4,5)
 * when(1){
 *   in arrayList.toIntArray()->{
 *      print("1存在与arrayList数组中")
 *   }
 *   in 0..10 ->print("1属于0~10的范围中")
 *   !in 5...10->print("1不在5~10的范围中")
 * }
 * 用法四；通过(is 是/ !is 不是)来检查指定值是否是某个类型
 * when("abc"){
 *    is String->print("属于字符串类型的数据")
 *    !is Int ->pring("属于Int类型的数据")
 * }
 *
 * kotlin中的循环控制语句：return，break,continue.
 * 这些return，break,continue与java中的用法是一致的。但是它们再kotlin中可以与标签结合使用。
 * 在kotlin中任何表达式都能使用标签来进行标记，声明标签的语法:"标签名@"
 * 当循环控制语句 与 标签 搭配使用时,含义为：当前这个循环控制语句作用于该标签所指定的表达式上。
 * 例如
 * flag1@ for (i in 1..10) {
 *    for (j in 1..10) {
 *        if (true){
 *           println("$i + $j")
 *            break@flag1
 *        }
 *    }
 * }
 * 上面代码输出的结果是：1 + 1
 * 解析：首先按照标签语法声明了标签flag1,然后在内部的循环中 循环控制语句与标签搭配使用了,break@flag1 表示 break关键字
 * 作用于flag1标签指定的表达式，即 break关键字作用于flag1标签指定的最外层的循环，因此程序执行力第一轮循环之后就退出了
 *
 *
 * kotlin的inline。内联函数
 * 被inline修饰的函数都被称为内联函数，它有几个作用
 * (1)被调用的内联函数,在编译的时候会直接把内联函数中的代码逻辑直接替换在调用的地方,移除了方法入栈,出栈的开销。
 * 例如:
 * public inline fun test(){逻辑代码1} test是一个内联函数,因此任何调用test()的地方,都会在编译的时候,替换为内联函数内部的具体逻辑,相当于直接把内联函数的代码写在里面一样。
 * (2)对函数中的函数式参数进行优化。
 * 例如 fun method01(block:()->Int){block()};参数是需要传入一个匿名函数，当我们调用这个method01方法的时候,编译器实际上是把匿名函数转换成:
 * a.创建一个Function0对象.
 * b.把匿名函数中全部的逻辑移植到Function0对象的invode方法中。
 * c.调用该对象的invode方法
 * 为什么是创建Function0是因为匿名函数不需要传递参数,如果有一个参数那么自动创建的对象将变成Function01.
 * 由此可知,kotlin对匿名函数以及lambda表达式都是通过创建Function对象来执行的
 * 如果用inline修饰有函数式参数的方法时,那么调用该内联函数时,不仅会把内联函数的代码逻辑替换在调用的地方,同时还会展开函数式参数方法,把函数式参数的内部逻辑合理的替换在调用处。
 * [即:官方说法 inline修饰的方法，其函数式参数也是inline的]
 * 例如当method01()被inline修饰后,不仅仅把method01()的逻辑替换在调用处,连block匿名函数内的代码逻辑也被直接替换进去了。
 * 官方说法:inline对于"没有函数类型参数"的函数来说是没有性能提升的,只有对于"存在函数类型参数的函数,性能提升才会明显,因为避免频繁创建Function对象了"
 *
 * kotlin的局部返回
 * kotlin的局部返回通常都是针对"存在函数式参数的函数,当其函数式参数中内部逻辑代码存在return,break,continue等控制语句的情况"来说的。
 * 当一个函数被inline关键字修饰后,它就是内联函数,内联函数的函数式参数也是inline的,这时inline的函数式参数中关于返回的控制可以有两种:
 * （1）return;正常的跟随内联函数的特性,内联函数中的逻辑以及函数式参数中的逻辑都直接替换到 内联函数的调用处,因此return 就是直接作用在调用内联函数的函数上。
 *  同时该写法等价于:reture@调用该内联函数的顶层函数名。
 *  例如test()方法中调用了一个method01(block:()->{})内联函数,当我们在block的代码中写return,则该return是直接作用在test()上,也可以写return@test,这样写法也是直接作用于test()方法上。
 * (2)return@内联函数名;表示return最用在该内联函数的函数式参数自身上
 * 当一个函数不被inline关键字修饰,它就是一个普通的函数,它的函数式参数也就不是inline的了,那么这时函数式参数的return只能作用于函数式参数自身
 * (3)因为inline的函数式参数是可以自由选择reture的作用对象以及范围的,因此会通过return@标签名 的方式来 来代替break,continue两关键字的功能。
 *
 *注意:只有inline的函数式参数才可以自由决定Return是作用于顶层函数,哪个for循环或作用于函数式参数(指函数式参数自身,不是指声明函数式参数的那个函数)自身上,如何函数式参数不是inline的,那return只能作用于
 *  函数式参数自身,即只能写reture@内联函数名
 *
 *  kotlin的特殊注解
 *  kotlin中类中声明的属性,默认都是private修饰的
 *  @JvmFiled :这个注解表示:被修饰的属性将会是public修饰的,不会生成getter/setter方法。
 */
class KnowledgePoints04 {
    lateinit var call: (String) -> Int

    fun test() {
        call = { para ->
            print(para)
            0
        }
        method01 {
            print("内联函数的转换")
            0
        }

        innerFun {
            print("nihao")
            return@innerFun
        }
        print("调用了吗")
    }

    inline fun method01(block: () -> Int) {
        block()
    }

    inline fun innerFun(a: () -> Unit) {
        a()
    }
}