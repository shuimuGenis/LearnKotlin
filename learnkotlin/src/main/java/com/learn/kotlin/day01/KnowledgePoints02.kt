package com.learn.kotlin.day01

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc 资料来源：https://www.cnblogs.com/Jetictors/p/8031291.html
 */
/**
 * kotlin的可见修饰符
 * public:所有地方可见。当没有声明任何修饰符时，kotlin默认使用该修饰符。
 * internal:模块中可见。
 * protected:子类中可见
 * private:类中可见。
 *
 * 对于顶层函数的可见性修饰，只有protected是不可以修饰顶层函数的，因为protected是控制子类访问的修复符,顶层函数没有类的概念。
 * 对于拓展函数来说，private和protected修饰的成员/方法都是不能访问的，拓展函数本质是静态函数,其内部也是通过"外部访问的方式"调用对象的方法以及属性的，
 * 什么是"外部访问",就是"对象.方法名"或者"对象.属性名" 这种就是外部访问方式，内部访问是不需要通过"对象.XXX"的方式的,它直接就能拿到方法和属性(包括私有方法和私有属性)的。
 *
 * internal模块可见：是只编译在一组的kotlin文件。
 * ((1)同一个IDEA模块(2)同在一个maven/gradle工程里的文件(3)同在一个Module工程(4)同在一个Ant里的文件) 这些才可以访问internal修饰的成员。
 * 原因是：java中对于第三方库里私有的类,可以通过在项目中创建 与“包私有的类的包名”一致的类的方式,来轻松访问包私有中的类，kotlin的internal修饰符解决的就是java这种轻松访问
 * 包私有的 这种问题。
 *
 * 例如
 * android.support.v4.app.Fragment类,在java中我们可以根据fragment的包名创建目录"/android/support/v4/app",然后创建一个SafeFrament类放在我们创建的android/support/v4/app
 * 的目录下面，这样SafeFragment类就能访问"原android.support.v4.app"包下的类了，这就是java允许的,但在kotlin中通过internal关键来限制这种操作。
 */
class KnowledgePoints02 {

}