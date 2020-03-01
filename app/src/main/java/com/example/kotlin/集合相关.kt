package com.example.kotlin

import android.util.Log

class CollectionOperationTest {
    fun operationTest1() {
        Log.i("zui", "comming collection operation fun")
        //创建一个空的 不可变list集合
        val emptyList = listOf<String>()
        Log.i("zui", emptyList.toString())
        //创建一个 不可变的 只读 list集合
        val varargeCollectionList = listOf(1, 2, 3, 4, 5, 6)
        Log.i("zui", varargeCollectionList.toString())
        //创建一个 可变的 list集合
        val nList = mutableListOf<String>()
        nList.add("李锦")
        nList.add("我")
        nList.add("喜欢你")
        //循环数组
        for ((index, item) in nList.withIndex()) {
            Log.i("zui", "list index $index list item $item")
        }
        //截取数组
        val newSubList = varargeCollectionList.subList(0, 2)
        Log.i("zui",newSubList.toString())
        Log.i("zui",nList.subList(0, 2).toString())
    }
}