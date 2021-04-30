package com.learn.kotlin.day01

import java.io.File

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc
 */
/**
 * （1）kotlin 中的变量和常量。
 * kotlin是静态类型的编程语言,运行在jvm上。
 * kotlin是 强类型的语言，要求在声明变量和常量的时候，必须显示或隐式的指定变量,常量的类型,并且为其赋予初始值。
 * 隐式是指:声明的变量或常量时不指定它们的类型,而是声明的同时进行初始化,这样就kotlin能根据初始化的值来推断该它们的具体类型。
 *
 * 声明变量的语法：var 变量名:变量类型=初始值
 * 声明常量的语法：val 常量名:常量类型=初始值
 * val/var的特点：
 * val修饰的变量/常量:代表它是 私有的不能修改的只读属性,只能赋值一次。
 * var修饰的变量/常量:代表它是 私有的可读可写属,能重复赋值。
 * 它们特点如何体现：
 * 私有的-->属性是private修饰的
 * 不能修改-->属性是final修饰的
 * 可读-->属性有公开的getter()方法来获取属性
 * 可写-->属性有公开的setter()方法来设置属性
 * 因此
 * val 修饰的变量/常量。 它含义是的：属性是私有不可修改--> private final 变量名/常量名:数据类型，并有 public 数据类型 getter(){}
 * var 修饰的变量/常量。它含义是：属性是私有--> private 变量名/常量名:数据类型，并有 public 数据类型 getter(){} 和 public void  setter(数据类型){}
 *
 *
 * 变量和常量的区别是 ：变量可以多次赋值,重复赋值;常量只能赋值一次,赋值后不可修改
 *
 * 局部范围内的常量:局部常量,一般是指在方法中声明的常量，它即允许声明的同时赋初始值,也允许声明的时不指定初始值,只要在第一次使用之前赋初始值即可。
 * 类中的常量：它既允许声明的同时赋予初始值,也允许在类或构造器中赋予初始值。即对象初始化完毕之前赋予初始值即可。
 *
 * val,var的特殊用法:当它们被用在类的主构造器上修饰构造器参数时,表示:主构造器的参数即是构造器方法的参数,也是该类的属性,kotlin会根据主构造器的参数名在类中生成对应的属性以及它们的setter/getter方法;
 * 如果它们不被用于类的主构造器上面的话,则主构造器上的参数只是普通的方法参数,不会把它们变成类的属性字段的。val,var除了会存在与主构造上,其他次构造器上不能存在。
 *
 * val/var在顶层函数的声明情况：
 * 首先要明确 val/var两个修饰符的作用，val表示私有的不可修改的只读属性，var表示私有的可读可写的属性。
 * 因此val/var修饰的变量,不管是修饰属性,还是修饰顶层变量,它们的特性都是不变的；val修饰的属性是私有的,自动生成getter方法来获取属性;var修饰的属性是私有的,自动生成getter/setter方法来操作属性
 * 基于val/var的特性，
 * 当val修饰顶层属性时,表示修饰的属性是私有的,在顶层声明,则属性是静态的,val是不可修改的,则属性是final的,所以是 私有的不可修改的静态属性,即:private static final
 * 还没完,因为val会自动生成getter方法,因此生成的getter也是是静态方法,则生成方法的修饰符：public static final getxxx(){}
 * 同理
 * 当var修饰顶层属性时,表示修饰的属性是私有的,在顶层声明,则属性是静态的,var是可修改的,则属性没有被final修饰,所以是 私有的可修改的静态属性,即:private static
 * 还没完,因为var会自动生成getter/setter方法,因此生成的getter/setter也是是静态方法,则生成方法的修饰符：public static final getxxx(){}
 *
 * kotlin中const修饰符的含义：
 * var/var修饰的属性都是"私有的"这一特性,只是通过自动生成公开的setter/getter方法去控制访问的权利而已,其中val更是final的不可修改的。
 * 这样的设定对于变量来说是适合的，对于常量来说,大多数时候,我们是把常量定义成[公开的不可修改的静态常量],而不是"先把这个不可修改的静态常量"私有化,然后提供一个公开的静态方法去获取它,这样多此一举。
 * 那么声明一个[公开的不可修改的静态常量]应该怎么做。
 * (1)先把val自带的私有化特性移除，通过const关键字 把val自带的private变成public
 * (2)给常量赋予静态修饰。kotlin中没有static关键字,kotlin只有三处地方可以赋予属性"静态修饰"
 * a)在顶层声明字段/方法都是静态的 b)objec修饰的类 中的字段是静态的,但方法不是静态的且方法是public final的 c)companion object修饰的类中的字段是静态的,但方法不是静态的且方法是public final的 。
 * OK,完成了声明一个[公开的不可修改的静态常量]之后,那么kotlin就不会自动为该常量生成公开的getter/setter方法了,已经没必要生成了。
 * 另外就算你不记得 这三处可以赋予静态修饰的地方，那你也不会写错，因为
 * const关键字只允许在下面三处地方使用:
 * a)在 顶层 中使用 b)objec修饰的类中使用 c)companion object修饰的类中使用。
 *
 * 因此当你想把val修饰的字段由 "private static final" 修改成 "public static final" 修饰时,
 * 你就在能【能赋予字段静态修饰的地方】写:const val 变量名:变量类型
 * 允许使用const修饰的地方:
 * 1.在顶层声明常量：const val NUM_ONE="one"
 * 2.在object修饰的类中声明常量：object Test{ const val NUM_TWO="two" }
 * 3.在伴生对象中声明常量:class Test{ companion object{ const val NUM_THREE="three" }}
 * 4.const是只能修饰 基本数据类型和String类型。
 *
 * object类-->"除了【属性是私有静态的】之外,所有【方法都不是静态】的,都是通过自动生成的唯一单例对象(INSTANCE变量指向了该单例对象)去访问的"
 * 官方提醒：
 * (1)object修饰的类，类是public final修饰的；
 * (2)object类中所有的属性都是静态的,但是属性的getter/setter方法不是静态的,由public final修饰的,符合第三点。
 * (3)object类中所有的方法(包括属性的getter/setter)都不是静态的,方法是属于对象的，是public final修饰的,
 *
 * 延迟初始化
 * kotlin中声明的变量/常量基本都是要及时进行初始化操作的,特别是在类中声明的属性必须在对象创建完成之前完成初始化,那么能不能延迟初始化的时机呢？是可以的
 * lateinit关键字：能够让被修饰的属性延迟初始化。
 * lateinit关键字修饰的变量必须满足这些条件:
 * (1)必须是可读可写的变量，即var修饰的变量
 * (2)不能修饰可空类型
 * (3)不能修饰基本数据类型。在kotlin中 String类型 不属于基本数据类型
 * 注意:虽然lateinit修饰的变量可以延迟初始化,但要主要必须要使用它之前进行初始化,要不然抛出"未初始化异常"
 * 那么,怎么知道 lateinit修饰的变量是否初始化了呢?例如声明了一个lateinit var变量给外界初始化,那么外界有没有初始化是无法得知的
 * lateinit var修饰的变量都会自动增加一个拓展属性 isInitialized。true表示初始化成功。
 * 例如 lateinit var name:String; if(naem.isInitialized){print(name)}\
 *
 * lazy高阶函数
 * 使用lateinit关键字修饰的变量进行延迟初始化,它们是可以重复赋值的
 * 使用lazy()高阶函数进行延迟初始化的属性,特点是只有程序第一次使用该变量时初始化一次,后续将不会进行初始化。
 * lazy高阶函数使用限制：
 * 只能用在 "可读变量"上,即只有val修饰的变量能使用，可读可写是不能使用lazy()函数的。
 * lazy{}函数不能自动推导类型。因此使用lazy{}函数的固定格式：val 变量名：变量类型 by lazy{ reture该变量类型的对象}
 *
 */
/**
 * (2)整型
 * kotlin中整型类型有Byte（-128~127,占8位），Short(-32768~32767,占16位),Int(-2147483648~2147483647,占32位),Long(-2^63~2^63-1,占64位)。
 * kotlin的整型类型兼容java中的整型基本数据类型和它们对应的包装类型。
 * 如：
 * 当在kotlin中定义一个整型的变量时,如果类型[没有贴着]"?"可空符号的话,则该整型类型对应Java的基本数据类型。val age:Byte=8；这里的Byte对应java的基本数据类型byte。
 * 当在kotlin中定义一个整型的变量时,如果类型[贴着]"?"可空符号的话,则该整型类型对应java的包装类型。val age:Byte?=8;这里的Byte对应java的包装类型Byte。
 *
 * 在kotlin中声明一个整型的变量或常量时没有指定其数据类型,默认是Int类型。
 * kotlin中整型数值的表示形式有三种：十进制，二进制，十六进制。
 */
/**
 * (3)特殊的符合
 * ?-->可空类型符号。可空类型符合用于表示当前的变量是否可以赋值为null。如 var name:String?这行代码表示:变量name是可以赋予null值。
 * 而var name:String;则表示变量name不可以赋予null值,如果anme是局部变量,则必须在第一次使用它之前赋初始值;如果是类的常量,则在类或构造器中赋予初始值。
 *
 * ?.-->安全调用运算符。用于检查当前执行调用的变量是否为null,是null则不调用该变量的任何方法并会返回一个null值;不为null则调用该变量的方法。
 * 如 var name:String?="shui";name?.length(); 其中name?.length()表明:如果name为null则返回一个null值,name不为null,则返回length()的值。
 *
 * ?:--> Elvis运算符。用于检查当前执行结果为null时返回一个默认值,反正返回执行结果的值。如
 * person.name?:"小明"; 该行代码表示:如果person的name字段的值为null,则返回默认值"小明",不为null则返回name字段的值。
 *
 * as?-->安全转换运算符。用于进行类型强制转换时,类型不合适,强转失败的情况下,返回一个null值。如
 * person as? com.learn.kotlin.bean.Student;该行代表表示:尝试强制转换person对象的类型为Student类型,如果成功则返回该对象的student类型,如果强转失败,则返回null。
 *
 * "*"星符号-->可变参数展开操作符。用于把kotlin的数组中的元素分散开一个个进行 传递。如,声明 fun test(vararg element:String){代码};因为test函数的参数是一个可变参数,当
 * 我们把数组Array<String>类型的对象传递给test()函数时,直接传递是不行的,必须在Array<String>前面+"*"星符合,代表打散Array<String>数组,把数组中的元素一个个传递进去。
 *
 * is-->类型检查运算符。用于判断对象是否是某个类型的实例,相当于Java中的 instance of。 !is表示对象是否不是某个类型的实例。当我们使用is关键字判断了
 * 对于kotlin来说,当对象通过了is关键字的判断代表-->如果判断结果为true,说明该对象就是这个类型,那就没必要进行强制转换了,kotlin会自动进行智能转换,不需要手动进行强制转换了
 *
 * in-->范围包含运算符。用于判断,某个数据是否在指定数组/指定区域 内。
 */
/**
 * (3)浮点数
 * kotlin中浮点数有两种
 * Float：表示32位的浮点数,当精度要求不高时使用
 * Double：表示64位的双精度浮点数.精度要求非常高的情况下使用。
 *
 * 注意:只有浮点数类型才能表示科学计数的形式。
 *
 * 如果一个浮点数数值,没有指定浮点类型，那么它默认是Double类型的。如 val ro:Float=1.5；该行代码将会报错。因为1.5默认是Double类型的,Double类型不能直接赋值给Flout类型,、
 * kotlin不会进行自动装箱,自动拆箱。val ro:Float=1.5f;这样写才是正确的。
 * 因为kotlin不会自动拆箱,因此大范围的数赋值给小范围的数应该这样写：val root:Float=1.5.toFloat()
 * 小范围 赋值给大范围,也是同理的：val tempDouble = root.toDouble()
 *
 * kotlin中还提供了3个特殊的浮点数值:正无穷大，负无穷大，非数。
 * 任何一个正数除以0.0得到正无穷大
 * 任何一个负数除以0.0得到负无穷大
 * 0.0除以0.0得到一个非数
 * 所有的正无穷大都相等，所有的负无穷大都相等，所有的非数都不相等(包括它自己都不相等的)
 *
 */
/**
 * 字符型
 * kotlin中的字符 必须用单引号括起来。使用的是unicode的字符编码，支持世界上所有的书面语言。
 * 与java不同的是,在kotlin中Char是不能直接当成整数使用,Char要显式的调用对应类型的方法才能转换成数值:Char.toInt();
 * 当Char的字符是英文的时,可以进行大小写转换的：toUpperCase(),toLowerCase()
 * kotlin中是String是不可变的,string允许通过索引获取其中的字符如str[index].也可以对String字符进行循环遍历打印各个索引上的字符
 * 把String看做是 字符数组是可以的。
 */
/**
 * 数组类型
 * kotlin提供了原始数组类型,ByteArray,ShortArray,IntArray,LongArray,CharArray,DoubleArray,FloatArray。
 * 这些原始的数组类型，没有装箱的开销
 * 其他的类型：Array<Type>
 * 通过对应的数组方法创建数组。长度不可变，除了创建方式与java不同之外,其他特性是相同的。
 * kotlin中的这些原始数组类型所创建的数组对象在面对JVM平台时,将会转换成对应类型的数组。
 * 例如:IntArray数组对象在jvm上就会被转换成int[]数组。
 * Array<Type>在jvm上将会转换成 java中任意类型的数组-->Array<String>==String[],Array<Person>==Person[]
 *
 */
class KnowledgePoints01 {
    val name: String by lazy {
        "555"
    }

    fun testFile() {
        File("").readBytes()
        File("").walk().filter { false }.filter { true }.forEach {  }
    }
}