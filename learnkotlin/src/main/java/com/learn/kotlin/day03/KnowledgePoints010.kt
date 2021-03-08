@file:JvmName("Points010")
@file:JvmMultifileClass
package com.learn.kotlin.day03

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc
 *
 * 平台类型：
 * 在Java中，所有引用都可能为null，然而在Kotlin中，对null是有着严格的检查与限制的，这就使得某个来自于Java的引用在Kotlin中变得不再适合；
 * 基于这个原因，在Kotlin中，将来自于Java的声明类型称为平台类型（Platform Types)。
 * 对于这种类型（平台类型）来说，Kotlin的null检查就会得到一定的缓和，变得不再那么严格了。
 * 这样就使得空安全的语义要求变得与Java一致。当我们调用平台类型的对象的方法时，Kotlin就不会在编译期间施加空安全的检查，使得编译可以正常通过；
 * 但是在运行期间则有可能抛出异常，因为平台类型引用值有可能为null。
 *
 * 因为平台类型是有可能为null的,所以在"把一个平台类型赋值给val关键字修饰的变量时,是有可能为null的"
 * 这时,编译器会在赋值时生成一个断言，这会防止Kotlin的不可空变量持有null值；
 * 同样，这一点也适用于Kotlin方法参数传递，我们在将一个平台类型值传递给方法的一个不可空参数时，也会生成一个断言。
 * 总体来说，Kotlin会竭尽所能防止null的赋值蔓延到程序的其他地方，而是在发生问题之处就立刻通过断言来解决
 *
 * 我们都知道kotlin的顶层函数或者属性,编译生会根据顶层函数和顶层属性所在的文件的名称,生成"文件名+kt.class"文件，而该"文件名+kt.class"类将会是public final修饰的类，
 * 顶层函数和顶层属性将会是该"文件名+kt.class"类的静态方法和静态属性。因此，java调用kotlin的顶层函数或顶层属性只需要按照 Java中静态方法和静态属性的调用方式访问即可。
 * 因为编译器是根据文件名去生成class类的，那么我们可不可以让编译器按照我们 定义的名字去生成class类呢？
 * 通过在文件的第一行:@file:JvmName("类名") 进行声明之后，编译器就按照我们定义的名称去生成class类了。
 * 因为我们可以给kotlin文件定义 自动生成的类的名称,通过@file:JvmName("类名")方式,但如果有多个类都定义了相同的名称呢?这时可以在文件的第二行使用
 * @file:JvmMultifileClass 这样相同名称的类，将会进行相互融合在一起。
 *
 */

class KnowledgePoints010 {

}