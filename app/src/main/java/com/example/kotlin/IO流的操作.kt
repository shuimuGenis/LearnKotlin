package com.example.kotlin

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.nio.charset.Charset

class IOStreamDemo {
    /**
     * 这种写法基本上就是 把 java的写法 搬过来了
     */
    fun IOTest1() {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader(File("build.gradle")))
            var line: String?
            //kotlin的while语句中 不允许条件判断的时候进行赋值
            while (true) {
                //因此把退出循环的逻辑写在这里. “reader.readLine() ?: break”表示 如果为空的时候 执行break语句
                line = reader.readLine() ?: break
                logInstance(line)
            }
        } catch (e: Exception) {
            print(e.printStackTrace())
        } finally {
            try {
                reader?.close()
            } catch (e: Exception) {
                print(e.printStackTrace())
            }
        }
    }

    /**
     * 当我们不想写 io流关闭操作的时候，我们可以 用user拓展方法
     * user()内部会判断 使用该拓展方法的对象 是否实现了 clonable接口 实现了的话 就可以调用close方法了
     */
    fun IOTest2() {
        try {
            var reader = BufferedReader(FileReader(File("build.gradle")))
            reader.use {
                var line: String?
                while (true) {
                    line = it.readLine() ?: break
                    logInstance(line)
                }
            }
        } catch (e: Exception) {
            logInstance(e.message!!)
        }
    }

    /**
     * 更加简化的写法。
     */
    fun IOTest3() {
        File("build.gradle").readLines(Charset.defaultCharset()).forEach { logInstance(it) }
    }
}