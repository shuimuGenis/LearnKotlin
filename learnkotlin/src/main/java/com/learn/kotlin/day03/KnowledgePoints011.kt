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
 * 上面总结的可能有点错误，先放着。下面是看过扔物线大神的视频之后写的关于协程的知识。
 * 程序中所有的代码都是跑在线程中的，线程是跑在进程中的，而协程它不是什么空中楼阁，也不是独立于线程和进制之外的新东西，协程它也是与其他代码一样，跑在线程中的。
 * Android系统中在主线程进行网络请求会抛出NetWorkOnMainThreadException异常，同样的如果协程是运行在主线程中，在主线程中运行的协程如果发起网络请求也是会抛出NetWorkOnMainThreadException异常的。
 * 因此，协程也是需要切换线程的。因为协程需要切换线程，因此协程肯定会提供用于切换线程的相关方法。如果我们不指定线程，协程就在调用它的线程上执行。
 * kotlin中的协程，就是一套封装好的用于切换线程的API，能够很方便的协助我们切换线程。协程就跟Android提供的AsyncTask一样，帮我们处理了主线程和子线程之间的切换，它们只是用法不同。
 *
 */
class KnowledgePoints011 {
    fun test() {
        UserInfo("xiaoming",18).apply({this.name})
    }
}