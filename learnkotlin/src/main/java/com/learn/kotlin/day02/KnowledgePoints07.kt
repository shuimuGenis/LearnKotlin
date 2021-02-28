import com.learn.kotlin.bean.Student

/**
 * kotlin 接口
 * kotlin 声明一个接口使用 interface 关键字。
 * 语法:interface 接口名
 * kotlin中的接口 可以声明属性但不能给属性赋予初始值.可以用类中重写属性访问器(即getter/setter方法)的方式重写接口中的声明的属性,
 * 但接口中属性访问器是不存在 幕后字段field。
 * 普通类是存在 幕后字段field 。例如: get(){return field}/setter(value){field=value}
 *
 * kotlin的函数式接口(SAM接口)
 * 只有一个抽象方法的接口就算函数式接口，所有函数式接口都可以写成lambda表达式的样子,因此如果lambda表达式的签名能与某个函数式接口的签名一致,则能自动见lambda表达式转换成该函数式接口的实现。
 * 因为所有函数式接口都能写成 lambda表达式的样子,因此不同函数式接口写成lambda之后的样子可能是一致的,所以lambda表达式是能随意转换成任意与签名其符合的函数式接口
 * 例如
 * 定义接口1--> interface Test01{ fun run():Int{}}
 * 定义接口2--> interface Test02{ fun call():Int{}}
 * Test01转换成lambda 基本形式都会是这样的：()->:Int{return Int}
 * Test02转换成lambda 基本形式都会是这样的：()->:Int{return Int}
 * Test01于Test02的lambda 其实是一致的,那么对于lambda来说 它的签名同时符合Test01,Test02两个接口,所以[lambda表达式是能随意转换成任意与签名其符合的函数式接口]
 *
 * kotlin 拓展函数。
 * 类的扩展 格式很简单：(各种修饰符) fun 类.方法名(参数列表){}
 * 通过类扩展定义的方法,就好像这个类本身就定义了该方法一样。
 *
 * 拓展函数本质上是 静态函数。
 * 在这个拓展函数的定义中 fun String.cxIsNotEmpty(): Boolean {return this.isNotEmpty()}
 * String是接收者类型,this是接收者类型的对象，
 * 我们说 kotlin的拓展函数本质上是静态函数,那么编译生成的java代码是怎样的呢?编译生成的java静态方法大概是这样的：
 * public static Boolean cxIsNotEmpty(String obj){ obj.isNotEmpty}
 * 可以看到,kotlin生成的java代码静态函数,为了实现在任意对象中增加拓展方法,一定会有"第一个参数的类型是 接收者类型,传入接收者类型的对象，内部代码也只是调用接收者对象的方法而已"
 * 根据生成的java代码原型,可知：拓展函数是不能被子类重写的..因为它是静态方法不属于对象
 * 另外，拓展函数强调的是"对指定类型的类进行函数拓展",
 * 注意:当拓展函数与被拓展的类中的某个成员方法签名(方法名与参数名)一致,则以该类的成员方法为主；如果拓展函数只是方法名与类中某个方法相同，但是参数不同,则将根据参数选择性调用。
 *
 * 除了给类定义拓展函数外，也可以定义拓展属性，同时还可以给 伴生对象定义拓展函数以及拓展属性。
 *
 * kotlin 数据类。
 *
 */
class KnowledgePoints07{

}