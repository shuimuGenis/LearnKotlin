package com.learn.kotlin.day03

import UserInfo
import android.app.Person
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc
 *
 * kotlin 协程
 * 协程的特点(1)协程是依赖于线程的,当协程所在的线程已经执行完毕之后,协程也会跟着关闭。线程不会因为协程中含有耗时任务而去等待协程完成,只有线程执行完成,线程就会自动销毁,协程也会跟着销毁。
 * 例如:在一个线程启动后立即开启了一个协程,假设协程执行任务逻辑需要3秒,线程在协程运行的同时继续往下执行代码逻辑,假设线程所执行的代码逻辑只需要1秒完成,这时协程还有2秒才能完成任务,因为线程执行的任务1秒完成了,线程就开始自我销毁,线程不会
 * 去等待协程执行完毕。因为线程销毁了,协程的根基不存在,则协程也销毁了。
 *
 */
class KnowledgePoints011 {

    fun test() {
        UserInfo("xiaoming",18).apply({this.name})
    }
}