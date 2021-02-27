/**
 * kotin中的类
 * 定义一个类的关键字为：class
 * kotlin中声明类会比java更加灵活
 * 与java相同的声明方式：
 * class Person{
 *     //属性
 *     //函数
 *     //内部类
 *     //等等
 * }
 * kotlin中允许,但我们声明的类,没有任何内容的时候是可以省略大括号的
 * 例如：class Person
 *
 * kotlin的类会存在一个主构造函数以及零个或多个次构造函数，主构造函数是类头的一部分。
 * class Person(name:String,age:Int){
 *      constructor(name:String,age:Int,role:Int):this(name,age)
 * }
 * 对应主构造函数,主构造函数的函数体是init{}代码块，我们在里面写主函数的初始化逻辑
 * 次级构造函数必须直接或间接的调用主构造函数，次级函数有自己的函数体。
 * 当主构造函数的参数被val/var修饰时,表面当前参数即是构造器的方法参数,也是该类的成员属性,将会生成对应的setter/getter方法
 *
 * 主构造函数默认值的情况：
 * 当主构造函数的"所有方法参数"都存在默认值的情况下,那么当前类将会存在一个无参构造函数,该函数使用的是主函数的默认值
 * 同理，当次级函数的"所有方法参数"都存在默认值的情况下,那么当前类也会存在一个无参构造函数,该函数使用的是次级函数的默认值
 * 特殊情况,当主函数与次级函数同时存在,同时主函数/次函数 它们的所有参数都有默认值,这时生产的无参构造函数使用的是主函数的默认值,以主函数为准。
 *
 * 类中的属性
 * 在类中声明的属性,可以用var/val关键字修饰，var修饰的属性自动生成getter/setter方法，val修饰的属性只会自动生成getter方法,不允许存在setter方法
 * 当我们想重写属性的getter/setter方法时
 * 直接在属性下方进行重写：
 * getter方法的重写为：get(){}
 * setter方法的重写格式：set(value){field=value}。set(value)这种写法是kotlin特有的,括号中的value不需要进行参数声明,也不需要赋值。它仅仅
 * 代表"将任何类型的值 赋值 给 当前属性"
 * 普通类中存在幕后字段field，接口中的属性是没有幕后字段field
 *
 * kotlin中"=="和"==="的区别
 * "=="比较的是内容是否相等
 * "==="比较内存地址是否相等
 * 在类中,var修饰的属性 getter方法 的可见性修饰符是不可以修改的。val修饰的 不能有setter方法
 *
 * 
 */
class KnowledgePoints05 {
    var name: String
        get() {}
        set(value){}
}

class Person(name: String, age: Int) {
    constructor(name: String, age: Int, role: String) : this(name, age) {

    }
}

