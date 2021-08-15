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
 * 上面了解了协程是什么之后，我们来看看协程的具体实体。
 * coroutineScope.launch(Dispatchers.IO){
 *     ...代码逻辑...
 * }
 * 这个launch函数它的具体含义是：我要创建一个新的协程，并在指定线程上运行该协程。那这个被运行的协程要做什么工作呢?
 * 协程的工作内容就是我们传给launch这个方法的闭包函数中的内容：“{ ...代码逻辑...}” 。
 * 协程目的就是把这个闭包函数的内容“{ ...代码逻辑...}”执行完。
 *
 * 协程的非阻塞式挂起。
 * 非阻塞式是什么意思?非阻塞式就是指：一个线程能够把任务切换到别的线程去执行就是非阻塞式。
 * 比如，我用Java的线程正在执行任务，遇到需要网络请求的时候，我直接创建一个Thread把这个网络请求放到子线程去执行，那么对于当前这个线程来说，它遇到耗时的网络请求时
 * 能够把耗时任务放到别的线程去执行，即便是因为我这个程序员手动在里面写的创建线程的逻辑，但是java语言允许我这么去写，允许我这么写了之后能切换线程，
 * 那么对于这个线程来说，这就是非阻塞式的。只不过java是通过程序员手动写上创建Thread来实现非阻塞式的。
 * 所以协程的非阻塞式不是协程特有的。
 * 再说了，一个单线程执行任务，遇到耗时任务时就直接执行，那耗时任务肯定就会卡住这个线程，要花费这个单线程大量的时间。
 * 对于协程也是的，我们创建一个协程，如果遇到耗时任务直接让这个协程去执行，那么这个协程也是会卡在这里，花费大量时间去处理耗时任务的，不也和线程一样就是会阻塞吗?
 * kotlin协助之所以宣传它自己是非阻塞式的，是因为协程提供了很方便的切换线程的API而已，也很方便的在切换线程之后能够切回来，
 * 这一切都被协程隐藏起来了形成一个API方便使用而已。
 * 这就是kotlin协程的非阻塞式，能够方便的切线程再切回来。
 * 前面说了什么是非阻塞，那么非阻塞式挂起呢?
 * 其实前面已经说了的，现在重点指出来：能够方便的切线程再把线程切回来 就是非阻塞式挂起。
 * 如果你切完线程再切回去都是全自动的，那就更方便了，这也是非阻塞式挂起。
 *
 * 补充一下：耗时任务基本上分两类：CPU的计算耗时，网络IO耗时。
 * CUP计算耗时就是指:我们平时写的那些业务代码啊，逻辑代码啊，如果写的逻辑特别不好，即使你的这些业务逻辑单个拧出来看都不是耗时操作
 * 但是业务逻辑中出现大量重复代码，或者你写的业务逻辑本身就效率不高，这也是耗时的。
 * 网络IO耗时：就是我们常说的网络请求啊，文件IO读写啊，数据库查找和存放啊等等。
 * 补充两下：
 * 虽然上面说到，kotlin协程的非阻塞式实际上是切线程，但是这是在JVM和Android平台的前提下，协程的非阻塞只能靠这个实现，
 * kotlin本身的定位是运行在多平台上的，那么别的平台对于kotlin的协程的实现可能有所差别，也许真的变成了单线程执行呢?啊哈哈哈。
 */
class KnowledgePoints011 {
    fun test() {
        UserInfo("xiaoming",18).apply({this.name})
    }
}

fun main() {
    GlobalScope.launch {
        postItem(Item("item"))
        println("done")
    }

    println("in main")
    Thread.sleep(2000)
}

suspend fun postItem(item: Item) {
    val token = requestToken()
    val post = createPost(token, item)
    processPost(post)
}

suspend fun requestToken(): String {
    print("Start request token ...")
    Thread.sleep(1000)
    println("... finish request token")
    return "token"
}

suspend fun createPost(token: String, item: Item): String {
    print("Start create Post ... $token, $item")
    Thread.sleep(500)
    println(" ... finish create Post")
    return "ResponsePost"
}

fun processPost(post: String) {
    println("process post, post=$post")
}

data class Item(val i: String = "item")