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
 *
 * kotlin中的类默认是"不可继承"的,只有open关键字修饰的类才是"可继承"的
 * 注意一下：子类与父类继承关系时,构造函数的两种特别写法
 * (1)class ShuiIdActionImpl constructor(user:String): ShuiBaseAction<String>(){代码}
 * (2)class ShuiIdActionImpl: ShuiBaseAction<String> { constructor(user:String):super(){} ....}
 * (1)和(2)都是声明构造函数的方式,它们两个等价,
 * (3)class ShuiIdActionImpl: ShuiBaseAction<String>(){constructor(user:String):super(){} ....} 这样写却是报错的。只有把父类旁边的括号移除才是正确的语法。
 * 所以子类在继承父类时,父类旁边声明了(),那么子类的次级构造器就不能直接super调用父类的构造器了,必须把父类旁边声明的()删除才行。
 *
 * 当一个类是可继承的(即open关键字修饰的类或者是抽象类),那么它可以存在派生类。但是open关键字修饰类的时候,只表示该类可以被继承,并没有表示该类的属性/和方法可以重写。
 * 想要其子类能够重写方法或属性,则就必须用open关键字修饰 方法/属性才行。属性覆盖的时,如果父类的属性是open val（表示可重写的私有的只读属性）那么子类可以用 var/val修饰符覆盖，反之，则不行
 *
 * kotlin中的子类 父类初始化顺序
 * 首先来了解java的子类父类初始化顺序：
 * Java程序初始化的顺序:父类静态变量 -> 父类静态代码块 -> 子类静态变量 -> 子类静态代码块 -> 父类非静态变量 -> 父类非静态代码块 -> 父类构造器 -> 子类非静态变量 -> 子类非静态代码块 -> 子类构造器。
 * 那么kotlin中呢
 * init代码块 --》java代码块
 * companion object-->Java中的静态方法，静态变量，companion中的init代码块==>java中的静态代码块。
 *
 * kotlin中声明的内部类默认是静态内部类，只有inner关键字修饰的内部类才是非静态内部类。
 * 在kotlin中,非静态内部类调用外部类的方法或者属性时,应该这样写：super@外部类名.函数名() / super@外部类名.属性
 *
 * kotlin的伴生对象
 * kotlin通过 companion object 修饰符来定义一个伴生对象。伴生对象内部的方法和属性都是static修饰的，静态的，每一个类中只能有一个伴生对象。
 * 注意:定义伴生对象的格式: companion object [伴生对象名]{代码逻辑} 。虽然声明伴生对象时有定义伴生对象的名称,但是伴生对象的名称其实是没有任何意义的,我们通常都"省略或不定义伴生对象名"
 * 例1 伴生对象内声明一个 val name:String="小明",则这行代码 的理解为 : "val的私有的不可修改"+"伴生对象内部属性是静态的" = private static final String name ="小明";同时生成val自带的生成getter方法
 * 例2 伴生对象内部声明一个 const val name:String="小明",则这行代码的理解为:"const关键字把val自带的private替换成public"+"伴生对象内部属性是静态的"=public static final String name ="小明"
 * 伴生对象的含义是，工厂方法，即对外提供静态的方法来访问类中的方法,执行类中的逻辑。本质不是让我们把 常量定义在里面的.定义常量或静态方法推荐 在顶层声明。
 */
class KonwledgePoint06(userName: String, age: Int, address: String) {

    constructor(userName: String) : this(userName, 18, "广东") {

    }
}