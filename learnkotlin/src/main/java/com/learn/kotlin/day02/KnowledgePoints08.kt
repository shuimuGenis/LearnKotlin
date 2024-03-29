package com.learn.kotlin.day02

import UserInfo
import com.learn.kotlin.bean.Student

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc
 *
 * kotlin 泛型
 * 通常情况下，我们使用具体的类就能够完成业务逻辑了，但是经常会写一堆应用于多种类型的代码，这些代码如果每一个类型都写一边就会很臃肿难以维护
 * 因此引入了泛型。即"将具体的类型进行参数化"就是参数化类型，就是泛型
 * 泛型本质定义是:"令X=某个类型的类，且X的范围是任意类型，使用时才确定X的具体类型，我们称X为泛型/参数化类型"。而一般在泛型类中写的逻辑是,当某个数据的类型为X的情况下,应该进行怎样的逻辑处理。
 * (1)基本使用。与Java一样
 * 在类/接口上声明泛型类型:interface IAnimal<A>{}
 * 在方法上声明泛型:fun <T> initAnimal(param:T){
 * }
 * (2)约束类型
 * 因为泛型的本质定义太过模糊,任意对象这个范围过于庞大时,规定泛型必须在某个范围内，这样就出现了泛型约束。泛型约束的本质是"规定X=某个对象的类型，且X的范围是约束的"
 * 那么如何约束范围呢？通过"继承"。例如: fun <T:IAnimal> actionAnimal(t:T){} 其中"<T:IAnimal>"即使泛型约束,规定了泛型T的上界，T的类型只能是IAnimal的子类,而
 * IAnimal为泛型T的上界类型。
 * 还可以通过where关键字约束 泛型多个上界。
 * 注意，kotlin中 泛型默认的上界类型都是"Any?"。
 * (3)类型擦除
 * kotlin的泛型只存在于编译期，运行期间类中所有使用到泛型的地方都被替换成具体的类型了，运行期间类是不保留泛型信息的，运行期间泛型被擦除,我们获取的是Array类型，是没有任何泛型信息的！
 * 这点于java是相同的。例如:Array<Int>,Array<String> 这样的类型,虽然声明了泛型类型是Int，String，但是仅仅在编译期进行安全检查，运行期是不保留任何泛型信息的，泛型信息都会被擦除，最后我们拿到的对象类型都是Array类型，是没有任何泛型信息的。
 * 这样的话,对于一个在运行时中的Array类型的对象,你根本不知道它泛型具体已经变成了什么类型，但是我们能拿到具体的泛型所指向的类型是因为内部做了强制转换.例如：Array<Int>,Array<String>,Array<Float>
 * 这就是 泛型的类型擦除带来的影响
 * 但是呢,kotlin通过 reified 关键字和inline关键字 做到了"智能的把泛型转换成使用时所指定的类型",因为转换成具体使用的类型了,自然就不存在什么泛型不泛型的,擦除也就不存在了。
 * 先介绍kotlin绕过jvm泛型擦除的原理,之后再展示用法。
 * 原理是：
 * 我们都知道内联函数的原理，编译器把实现内联函数的内部代码动态插入到每次调用的地方。那么实化的原理正是基于这个机制，
 * 每次调用带实化类型参数的函数(每次调用带泛型的函数)时，编译器都已经知道在此次调用中,该函数中的泛型具体是什么类型。
 * 所以编译器在插入inline函数的内部代码时,直接都把用到泛型的地方替换成具体的类型,因此最后生成的字节码中,根本看不到泛型的影子,也就不存在泛型擦除了。
 * 例如: 有一个泛型方法:
 *  inline fun <reified T> changeData(data: T){
 *      va temp: T = datal
 *      print(T::class.java)
 * }
 * 还有一个方法内部调用了该inline函数：
 *  fun testChangeData(){
 *    changeData<UserInfo>(UserInfo("小米",0))
 *  }
 * 编译器处理该inline函数时,做了两个操作：
 * (1)直接把inline函数内部代码: val temp: T = data; print(T::class.java)替换到调用处
 * (2)因为调用的地方指定了具体的泛型类型,因此把内部代码中用到泛型的地方全部换成具体的类型。
 * 则T::class.java换成UserInfo::class.java; val temp: T = data换成 val temp:UserInfo = data
 * 整个内部代码变成：
 * val temp:UserInfo = data
 * print(UserInfo::class.java)
 * 因此,替换过后,泛型从此不存在了,也就没有泛型擦除了。
 * 明白了原理之后，我们就根据原理,把Gson的解析方法优化一下,下面是高级写法:
 * inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)
 *
 * (4)型变
 * 型变背景:[假设有A,B两个类,设B是A的子类(即B:A),声明List<A>,List<B>,因为B:A,所以List<B>中的是可以当作List<A>的,但是编译器不这么认为,编译器认为 List<A>,List<B>是两个不同的东西,不能把List<B>当作List<A>的]
 * 那么,kotlin提供了对这一情况的支持,out和in两个关键字用于告诉编译器 泛型的继承关系,泛型的上界和下界。
 * kotlin中的 out 和 in 就是 Java中的 ?通配符。
 * 【被out关键字修饰的泛型,表示该泛型是指定类型或指定类型的子类。当该泛型声明在类上时，只能出现在成员方法的返回值中,不能用作入参，原因是不确定传入的是什么类型，只知道是指定泛型类型的子类。被称作协变】-->生产者
 * 【被in关键字修饰的泛型,表示该泛型是指定类型或指定类型的父类。当该泛型声明在类上时，只能出现在成员方法的入参中,不能用作返回值，原因是不确定返回的是什么类型，只知道是指定泛型类型的父类。被称作逆变】--消费者
 * 就那背景中说的示例来讲解：因为A,B是继承关系,那么可以告诉编译器:"A是B的父类"这一点，通过out关键字来达成
 * class Animal<out D>
 * fun test() {
 *    val tempAnimalB = Animal<B>()
 *    var tempAnimalA :Animal<A> = tempAnimalB
 * }
 * 上面的代码中,Animal的泛型D是被 out关键字修饰的,
 * 含义是:被out关键字修饰的泛型,表示该泛型只有是指定类型或指定类型的子类，都能通过编译。
 * 声明处型变 是指在类上声明泛型,并且声明泛型是协变还是逆变
 * 使用处型变 是指在方法上声明泛型,并且声明泛型是协变还是逆变
 * 仅仅是声明的地方不同而已。
 */
class KnowledgePoints08<out R> {

    fun testChangeData() {
        changeData<UserInfo>(UserInfo("小米", 0))
    }

    inline fun <reified T> changeData(data: T) {
        var temp = mutableListOf(Student(), Student(), Student())
        addStudent(temp)
        var studentList = ArrayList<Student>()
        var userInfoList: ArrayList<out UserInfo> = studentList
        //这里演示了两种 out的用法哈。
        var shop01 = Shop<Student>()
        var shop02: Shop<UserInfo> = shop01
    }

    fun addStudent(fruit: MutableList<out Student>) {
        //获取的时候，只能是通过Any?获取，in关键字表示传入的必须是Student
        val any: Any? = fruit[0]
    }

    fun getUser(fruit: MutableList<out UserInfo>) {

    }
}

class Shop<out D> {
    //演示使用
    fun testA(): D? {
        //业务代码.....
        return null
    }
}