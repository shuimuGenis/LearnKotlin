package com.example.kotlin.coroutine

import java.util.concurrent.Executors

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc
 */
val pool by lazy {
    Executors.newCachedThreadPool()
}

fun AsynTask(block: () -> Unit) = fun() {
    pool.execute(block)
}