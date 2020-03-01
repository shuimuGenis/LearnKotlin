package com.example.kotlin.day06

class TextDemo06 {

    /**
     * 下面的写法等价于
     * fun add(x: Int): (Int) -> Int {
    return fun(y: Int): Int {
    return x + y
    }
     这种写法就叫做 科理化。科理化的本质就是 高阶函数。
     */

    fun add(x: Int) = fun(y: Int) = x + y

    fun modify(goods: Goods) = fun(price: Int): Goods {
        goods.price += price
        return goods
    }
}


data class Goods(var name: String, var price: Int)