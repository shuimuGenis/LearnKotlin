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
 */
class KnowledgePoints07 {
}