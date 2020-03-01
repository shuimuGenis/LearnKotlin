package com.example.kotlin.day06

/**
 * 对于泛型，无论是java 还是kotlin 都接口这个口诀:"Product extends Consume Super" 简称PECS。
 * 对应于java的 <? extends T> (定义泛型的上边界--生产者) 和 <? super T >（定义泛型的下边界---消费者）
 * 生产者 只能读；消费者只能写。
 * kotlin中也存在类似的 泛型概念： out T 和 in T 。
 * out关键字修饰的泛型 只能读不能写。对应的只能出现在返回值上。
 * in 关键字修饰的泛型，只能写不能读。对于的只能出现在 输入参数列表中。
 *
 * out关键字修饰的泛型，代表着 协变。即 子类 可以代替 父类
 * in 关键字修饰的泛型，代表着 逆变。即 父类 可以代替子类。
 *
 * 当我们想要获取 泛型的具体类型的class对象的时候。可以通过 inline 和 reified 两个关键字配合使用。
 *   inline fun <reified T> Gson.FomeJson(jsonSt:String){
         return Gson().fromJson(json, T::class.java)
     }
 * */

