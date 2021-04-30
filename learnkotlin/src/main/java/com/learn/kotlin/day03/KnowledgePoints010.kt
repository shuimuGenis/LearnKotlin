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
 * 关于@JvmFiel注解
 * kotlin中，类中定义的属性和字段,对kotlin来说是公开的，但是编译成java代码则是private修饰属性并提供公开的setter/getter方法。所以java调用kotlin的属性的时候，
 * 只能通过setter/getter方法去间接访问属性,如果我们希望能够直接访问类的属性而不是通过setter/getter这种间接的方式呢？,
 * 那就只能是要求编译器直接把属性编译器public修饰的 这样我们就能公开直接访问属性了。怎样做呢?
 * 通过@JvmFiel注解-->表示该注解修饰的属性,编译成java的时候,直接public修饰,直接让外部访问,
 * 不需要弄成"private修饰属性并提供公开的setter/getter方法"这种方式来间接访问。
 * 同理
 *
 * 关于 @JvmStatic 注解[该注解只能在object和companion_object这两个关键字修饰的类中才能使用]
 * kotlin中定义的单例类,通过object关键字定义的单例类,对kotlin来说,单例类中的属性和方法都能通过"类名.属性名/类名.方法名"的方式去访问,看起来就好像方法和属性是静态的,其实不是！！
 * 编译成java的时候则是"除了【属性是私有静态的】之外,所有【方法都不是静态】的,方法都是通过自动生成的唯一单例对象(INSTANCE变量指向了该单例对象)去访问的",
 * 所以java中访问将会是:"类名.INSTANCE.方法名"。那么,我就是想在单例类中定义一个静态方法,应该怎么做呢?
 * 在object修饰的类中,被@JvmStatic 注解注释的方法,都会增加静态属性,变成静态方法。变成静态方法就能直接外部进行访问,而不需要通过INSTANCE对象了。
 *
 * 伴生对象与@JvmStatic注解
 * 伴生对象的本质就是内部类,是 public static final修饰的内部类 ,而且该内部类是单例模式的,伴生对象中所有的方法都是通过单例对象去访问的。所以对kotlin来说,单例类中的属性和方法都能通过"类名.属性名/类名.方法名"的方式去访问
 * 但是对于Java来说,访问将会是"类名.Companion.方法名".那么,我就是想在伴生类中定义一个静态方法,应该怎么做呢?
 * 同样是@JvmStatic注解,被@JvmStatic 注解注释的方法,都会增加静态属性,变成静态方法就能直接外部进行访问,而不需要通过Companion对象了。
 *
 * 关于@JvmField
 * kotlin中给类定义属性都会是通过val/var关键字去声明的,虽然官方定义val是公开的任何地方都能访问的且是只读的；定义var是公开的任何地方都能访问的且是可读可写的；
 * 但是当kotlin代码要在jvm平台运行的时候,java应该如何去实现val/var这样的特性，或者说要求呢？
 * 先看val关键字。
 * val要求只读且是公开的,java实现这种方式是由很多种的[例如:直接public final 这样即公开也无法重复赋值],但Java所选择的方式是:
 * (1)java选择先把变量用private修饰,这样外界谁也无法访问,然后提供一个"公开的且无法随意修改的【即public final】"方法供外界访问该变量,从而达到"公开且只读"的效果
 * (2)步骤(1)只是达到了"对外界来说是公开且只读的",但对应类内部,仅仅private修饰还是不够的,类内部还是能随意修改这个private修饰的变量的值的,因此"给变量加上final关键字"从而达到"对类内部也是公开且只读"
 * 如果我希望 属性就是简单的[public final 这种方式达到"公开也无法重复赋值"的效果呢?],
 * 那就可以通过添加注解 @JvmField ---》这样属性就编译成:"public final修饰的属性"了,且不会生成任何方法,对外界对内部也还是"公开且只读"
 * 再看var关键字
 * var关键字要求"可读可写且公开",java实现这种方式由很多[例如:直接public修饰该变量即可],但是java选择的方式是:
 * (1)java选择先把变量用private修饰,这样外界谁也无法访问,然后分别提供"公开的且无法随意修改的【即public final】"的getter/setter方法供外界访问该变量,从而达到"公开且可读可写"的效果
 * (2)步骤(1)达到了"对外界来说可读可写且公开",对内部也是一样的,private关键字就已经能实现对内部是可读可写且公开了。
 * 如果我就是希望 属性就是简单的通过【public 修饰的方式来达到"可读可写且公开了"】
 * 那就可以通过添加注解 @JvmField ---》这样属性就被编译成"public 修饰的属性了",且不会生成getter/setter方法,对外界对内部也还是"公开且可读可写"
 *
 * 关于@JvmOverloads
 * 在kotlin中,我们可以给函数的参数定义默认值,当该函数的参数没有传递数值时则使用定义的默认值。通过这种方式可以进行方法重载。
 * 例如:fun toast(tag:String="zui",msg:String){};示例中的方法taost的参数tag是赋予了默认值的,使用的时候可以不给tag传值,仅仅传递msg即可
 * 但是kotlin的这种"参数默认值"在java中默认是不会识别的,但我们在java中调用"存在参数默认值的函数时,仍需要给[有默认值的参数]赋值"。参数默认值在Java中失去了它的意义,因为Java没有参数默认值。
 * 但如果我就是希望java于kotlin一般呢?或者希望java能够支持默认值呢？对此,kotlin给出了解决方案：@JvmOverloads注解
 * @JvmOverloads注解 的作用是:让编译器给存在参数默认值的函数生成多个重载方法,重载方法内部写明了传递的默认值。通过这种方式让Java"看起来支持了默认值,实际上只是取巧的通过方法重载实现而已"
 */
class KnowledgePoints010 {
    @JvmField
    var test: String? = ""

    fun test() {
        ActionMovie.action("")
    }

    @JvmOverloads
    fun test010(price: String = "10元", name: String) {
        print("price=$price;name=$name")
    }

    companion object {

        fun KnowledgePointsAction(){

        }
    }
}

object ActionMovie {
    @JvmField
    val actionKey: String = "1"

    @JvmStatic
    fun action(action: String?) {
        print(action)
    }

    fun action02(action: String?) {
        print(action)
    }
}