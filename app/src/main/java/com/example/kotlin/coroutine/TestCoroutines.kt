package com.example.kotlin.coroutine

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.kotlin.logInstance
import kotlinx.coroutines.Dispatchers
import java.net.HttpURLConnection
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc 不使用kotlin轻量级协程框架的情况下，如果自己定义协程框架
 */

/**
 * 定义一个支持传入 挂起函数 的方法。suspend关键字修饰的函数就是挂起函数
 */
fun 我要开始协程了(block: suspend () -> Unit) {
    block.startCoroutine(BaseContinuation(Dispatchers.Main))
}

/**
 * 这里一个 挂起函数。启动一个挂起函数并不会开启一个子线程。协程并不是帮我们直接开启线程的，这样协程就没有意义了。
 * 协程本质上就是在同一个线程上,在不切换线程的情况下能够异步执行任务,优势就是去除了线程与线程之间的切换,线程切换时消耗性能的，而协程是在本线程中进行异步。
 */
suspend fun 开始下载图片(url: String): Bitmap {
    return suspendCoroutine {
        AsynTask {
            logInstance("开始下载图片 方法被调用了")
            val httpURLConnection = URL(url).openConnection() as HttpURLConnection
            try {
                if (httpURLConnection.responseCode == 200) {
                    val requestBitmap = BitmapFactory.decodeStream(httpURLConnection.inputStream)
                    it.resume(requestBitmap)
                } else {
                    it.resumeWithException(NullPointerException("不是一个图片的地址"))
                }
            } catch (e: Exception) {
                it.resumeWithException(e)
            }
        }()
    }
}

/**上面是演示了一个正常的协程使用逻辑.但是我们不可能每次都来 定义一个suspend方法，然后每次都返回一个susendCoroutineh函数啊,因此对这个方法进行抽取*/

suspend fun <T> 我要开始异步了(block: () -> T) = suspendCoroutine<T> {
    AsynTask {
        try {
            it.resume(block())
        } catch (e: Exception) {
            it.resumeWithException(e)
        }
    }()
}

fun 我要进行图片下载了(url: String): Bitmap {
    val urlConnection = URL(url).openConnection() as HttpURLConnection
    if (urlConnection.responseCode == 200) {
        return BitmapFactory.decodeStream(urlConnection.inputStream)
    }
    throw java.lang.NullPointerException("错误的图片")
}