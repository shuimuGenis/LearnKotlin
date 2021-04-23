package com.learn.kotlin.day02

import UserInfo

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
 * kotlin的泛型只存在于编译期，运行期间类中所有使用到泛型的地方都被替换成具体的类型了，运行期间类是不保留泛型信息的，运行期间泛型被擦除,我们获取的是Array<Object>，因此我们 [无法判断一个对象是否是"某个声明了具体泛型类型的类"的实例]
 * 这点于java是类似的。例如:Array<Int>,Array<String> 这样的类型,虽然声明了泛型类型是Int，但是仅仅在编译期进行安全检查，运行期是不保留任何泛型信息的，泛型信息被擦除变成 IAnimal<Object>。
 * 这样的话,对于一个Array<Object>类型的对象,你根本不知道它是Array<Int>,Array<String>,Array<Float>,也就不知道Array<Object>中每个元素是Int还是String,所以循环数组时也只能每个元素都判断类型,然后来正确使用,避免类型转换错误的出现。
 * 这就是 泛型的类型擦除带来的影响
 * 但是呢,kotlin通过 reified 关键字和inline关键字 做到了"智能的把泛型转换成使用时所指定的类型",因为转换成具体使用的类型了,自然就不存在什么泛型不泛型的,擦除也就不存在了。
 * 先介绍kotlin绕过jvm泛型擦除的原理,之后再展示用法。
 * 原理是：
 * 我们都知道内联函数的原理，编译器把实现内联函数的内部代码动态插入到每次调用的地方。那么实化的原理正是基于这个机制，
 * 每次调用带实化类型参数的函数(每次调用带泛型的函数)时，编译器都已经知道此次调用中方法中的泛型具体是什么类型。
 * 所以编译器在插入inline函数的内部代码时,直接都把用到泛型的地方替换成具体的类型,因此最后生成的字节码中,根本看不到泛型的影子,也就不存在泛型擦除了。
 * 例如: 有一个泛型方法:
 *  inline fun <reified T> changeData(data: T){
 *      val temp: T = data
 *      print(T::class.java)
 * }
 * 还有一个方法内部调用了该inline函数：
 *  fun testChangeData(){
 *    changeData<UserInfo>(UserInfo("小米",0))
 *  }
 * 编译器处理该inline函数时,做了两个操作：
 * (1)直接把inline函数内部代码: val temp: T = data; print(T::class.java)替换到调用处
 * (2)因为调用的地方指定了具体的泛型类型,因此把内部代码中用到泛型的地方全部换成具体的类型。
 * 则T::class.java换成String::class.java; val temp: T = data换成 val temp:String = data
 * 整个内部代码变成：
 * val temp:String = data
 * print(String::class.java)
 * 因此,替换过后,泛型从此不存在了,也就没有泛型擦除了。
 * 明白了原理之后，我们就根据原理,把Gson的解析方法优化一下,下面是高级写法:
 * inline fun <reified T> Gson.fromJson(json: String) = fromJson(json, T::class.java)
 *
 * (4)型变
 * 型变背景:[假设有A,B两个类,设B是A的子类(即B:A),声明List<A>,List<B>,因为B:A,所以List<B>中的是可以当作List<A>的,但是编译器不这么认为,编译器认为 List<A>,List<B>是两个不同的东西,不能把List<B>当作List<A>的]
 * 那么,kotlin提供了对这一情况的支持,out和in两个关键字用于告诉编译器 泛型的继承关系,泛型的上界和下界。
 * 【被out关键字修饰的泛型,保留该泛型作为父类的能力,同时该泛型只能出现在成员方法的返回值中,不能用作入参。被称作协变】-->生产者
 * 【被in关键字修饰的泛型,保留该泛型作为子类的能力,同时该泛型只能出现在成员方法的入参中,不能用作返回值。被称作逆变】--消费者
 * 就那背景中说的示例来讲解：因为A,B是继承关系,那么可以告诉编译器:"A是B的父类"这一点，通过out关键字来达成
 * class Animal<out D>
 * fun test() {
 *    val tempAnimalB = Animal<B>()
 *    var tempAnimalA = tempAnimalB
 * }
 * 上面的代码中,Animal的泛型D是被 out关键字修饰的,含义是:被out关键字修饰的泛型,保留该泛型作为父类的能力,同时该泛型只能出现在成员方法的返回值中,不能用作入参。
 * 声明处型变 是指在类上声明泛型,并且声明泛型是协变还是逆变
 * 使用处型变 是指在方法上声明泛型,并且声明泛型是协变还是逆变
 * 仅仅是声明的地方不同而已。
 */
class KnowledgePoints08 {

    fun testChangeData() {
        changeData<UserInfo>(UserInfo("小米", 0))
    }

    inline fun <reified T> changeData(data: T) {
        val temp: T = data
        print(T::class.java)
    }
}