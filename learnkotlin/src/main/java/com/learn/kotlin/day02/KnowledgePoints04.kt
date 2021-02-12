/**
 *kotlin 中的控制语句
 * if语句
 * 在kotlin中 if语句是被看做一个表达式存在的
 * 用法一:与传统的java相同的写法
 * var num:Int =2
 * if (num==2){print("num==$num")}else {print("num 大于2")}
 * 用法二：作为三元运算符存在
 * val result=if(num>3) 2 else -1
 * 用法三：作为一个块 存在
 * val result=if(条件1){
 *      代码1
 *      代码2
 *      ...最后一行的结果作为条件1的返回值
 * }else if(条件2){
 *      代码1
 *      代码2
 *      ...最后一行的结果作为条件2的返回值
 * }else if(条件3){
 *      代码1
 *      代码2
 *      ...最后一行的结果作为条件3的返回值
 * }
 *
 *kotlin中的when语句
 * kotlin移除了switch语法,使用when进行替代,并且when比switch更强大
 * 用法一:基础用法,实现switch功能
 * when(5){
 *   1 ->{print("1")}
 *   2->{代码}
 *   3->{代码}
 *   4->{代码}
 *   else->{代码}
 * }
 * 用法二:实现switch的break功能。即当满足1,2,3条件时都执行代码1的逻辑，满足4,5条件都执行代码2的逻辑
 * when(5){
 *   1,2,3->{代码1}
 *   4,5->{代码2}
 * }
 * 用法三：通过(in 在/ !in 不在)关键字检查值是否在集合或者数组中国
 * var arrayList=arrayOf(1,2,3,4,5)
 * when(1){
 *   in arrayList.toIntArray()->{
 *      print("1存在与arrayList数组中")
 *   }
 *   in 0..10 ->print("1属于0~10的范围中")
 *   !in 5...10->print("1不在5~10的范围中")
 * }
 * 用法四；通过(is 是/ !is 不是)来检查指定值是否是某个类型
 * when("abc"){
 *    is String->print("属于字符串类型的数据")
 *    !is Int ->pring("属于Int类型的数据")
 * }
 *
 * kotlin中的循环控制语句：return，break,continue.
 * 这些return，break,continue与java中的用法是一致的。但是它们再kotlin中可以与标签结合使用。
 * 在kotlin中任何表达式都能使用标签来进行标记，声明标签的语法:"标签名@"
 * 当循环控制语句 与 标签 搭配使用时,含义为：当前这个循环控制语句作用于该标签所指定的表达式上。
 * 例如
 * flag1@ for (i in 1..10) {
 *    for (j in 1..10) {
 *        if (true){
 *           println("$i + $j")
 *            break@flag1
 *        }
 *    }
 * }
 * 上面代码输出的结果是：1 + 1
 * 解析：首先按照标签语法声明了标签flag1,然后在内部的循环中 循环控制语句与标签搭配使用了,break@flag1 表示 break关键字
 * 作用于flag1标签指定的表达式，即 break关键字作用于flag1标签指定的最外层的循环，因此程序执行力第一轮循环之后就退出了
 * 
 */
class KnowledgePoints04 {
    fun test() {
    }
}