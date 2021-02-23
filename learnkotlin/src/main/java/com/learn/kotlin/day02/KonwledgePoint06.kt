/**
 * kotlin的类
 * kotlin 中声明类的方式：通过 class关键字 +类名 的方式类定义的。
 * kotlin中所有的类都继承字Any类。就跟Java中所有的类都继承自Object类一样。
 * 当声明一个没有任何主体的类时:class UserInfo  这样就声明了一个UserInfo的类,该类没有任何主体,没有定义属性也没有定义方法,该类仅仅时默认继承了Any类而已。
 *
 * 构造函数
 * kotlin中类存在主构造函数以及次构造函数。主构造函数0个或1个，次构造函数0个或多个。主构造函数是属于类头的一部分,它跟在类名之后声明于定义，
 * 注意：当且仅当一个非抽象类没有声明任何构造函数(即没有声明主构造函数也没有声明次构造函数时),kotlin会默认给该类生成一个无参的主构造函数。
 * 注意:主构造函数的参数如果被val/var修饰,这时表示："主构造器的参数即是构造器方法的参数,也是该类的属性,kotlin会根据主构造器的参数名在类中生成对应的属性以及它们的setter/getter方法"
 * 如果主构造函数的参数没有被val/var修饰,那么主构造器上的参数只是普通的方法参数,kotlin不会把它们变成类的属性字段的。
 * 注意：只有主构造函数的参数能够被val/var修饰，次级函数可不行。
 * 主构造函数是不能包含任何代码的，因此通常把主构造函数的代码逻辑放在init{}代码块中,可以把init代码块看成主构造函数的方法体,自然主构造函数的参数是可以在init代码块中使用的。


 * 次级函数
 * 次级函数通过 constructor关键字声明: constructor(userName:String){}
 * 因为一个类可以存在0个或1个主构造函数,则当类存在主构造函数时,次级函数必须直接或间件调用主构造函数；当类不存在主构造函数时,则次级函数不需要直接或间接调用主函数,因为这时主函数根本就不存在,怎么调用呢?
 * 注意：init代码块实际上是属于主构造函数的一部分,当次级函数直接或间接调用主构造函数,那么主构造函数的init代码块将会优先执行,之后才执行次级函数的代码逻辑;即使该类没有声明主构造函数,当次级函数调用时、
 * 仍然是init代码块先执行完成之后,次级函数的代码才执行。
 *
 * 构造函数调用其他构造函数的写法为：
 * fun 构造函数名(构造函数参数):this(构造函数参数){}
 * 例如：constructor(userName: String) : this(userName, 18, "广东")
 * 如果调用父类的构造函数
 * fun 构造函数名(构造函数参数):super(构造函数参数){}
 * 例如：constructor(userName: String) : super(userName, 18, "广东")
 */
class User(userName: String, age: Int, address: String) {

    constructor(userName: String) : this(userName, 18, "广东") {

    }
}