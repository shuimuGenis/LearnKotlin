package com.example.kotlin.coroutine

import android.app.Activity
import android.widget.ImageView
import com.example.kotlin.R
import com.example.kotlin.logInstance
import kotlinx.coroutines.*

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc
 */

class TestCoroutines2(val contentView: Activity, val pictureUrl: String) {

    fun handlerOnClick() {
        val time1 = System.currentTimeMillis()
        logInstance("handlerOnClick2 step 1..")
        //指定协程在当前的线程执行
        GlobalScope.launch(Dispatchers.Main) {
            logInstance("handlerOnClick2 step 2..")
            val tempImageView = contentView.findViewById<ImageView>(R.id.showImg)
            logInstance("handlerOnClick2 step 3..")
            //创建一个新的协程并且立即启动执行
            var bitmapAsync = GlobalScope.async {
                logInstance("handlerOnClick2 step 4..")
                我要进行图片下载了(pictureUrl)
            }
            logInstance("handlerOnClick2 step 5..")
            //该方法表示，挂起当前协程,等async子协程执行完成之后恢复
            bitmapAsync.join()
            logInstance("handlerOnClick2 step 6..")
            //获取async子协程的结果
            val completed = bitmapAsync.getCompleted()
            logInstance("handlerOnClick2 step 7..")
            //切换协程到 主线程进行执行
            tempImageView.setImageBitmap(completed)
            val time2 = System.currentTimeMillis()
            logInstance("time = ${time2 - time1}")
            logInstance("handlerOnClick2 step 8..")//日志8在主线程执行
            //上面的 withContext方法切换的现在.将会对后面的方法产生影响..所以日志9 也是在main线程进行执行的
            logInstance("handlerOnClick2 step 9..") //该日志9也在主线程执行
        }
        logInstance("handlerOnClick2 step 10..")
    }

    fun handlerOnClick2() {
        logInstance("handlerOnClick2 step 1..")
        val time1 = System.currentTimeMillis()
        //指定协程在当前的线程执行
        GlobalScope.launch(Dispatchers.Unconfined) {
            logInstance("handlerOnClick2 step 2..")
            val tempImageView = contentView.findViewById<ImageView>(R.id.showImg)
            logInstance("handlerOnClick2 step 3..")
            //创建一个新的协程并且立即启动执行
            var bitmapAsync = GlobalScope.async {
                logInstance("handlerOnClick2 step 4..")
                我要进行图片下载了(pictureUrl)
            }
            logInstance("handlerOnClick2 step 5..")
            //该方法表示，挂起当前协程,等async子协程执行完成之后恢复
            bitmapAsync.join()
            logInstance("handlerOnClick2 step 6..")
            //获取async子协程的结果
            val completed = bitmapAsync.getCompleted()
            logInstance("handlerOnClick2 step 7..")
            //切换协程到 主线程进行执行
            withContext(Dispatchers.Main) {
                val time2 = System.currentTimeMillis()
                tempImageView.setImageBitmap(completed)
                logInstance("time = ${time2 - time1}")
                logInstance("handlerOnClick2 step 8..")//日志8在主线程执行
            }
            //上面的 withContext方法切换的现在.将会对后面的方法产生影响..所以日志9 也是在main线程进行执行的
            logInstance("handlerOnClick2 step 9..") //该日志9也在主线程执行
        }
        logInstance("handlerOnClick2 step 10..")
    }

    fun handlerOnClick3() {
        logInstance("handlerOnClick2 step 1..")
        val time1 = System.currentTimeMillis()
        var tempImageView: ImageView? = null
        //指定协程在当前的线程执行
        GlobalScope.launch(Dispatchers.Unconfined) {
            logInstance("handlerOnClick2 step 2..")
            logInstance("handlerOnClick2 step 3..")
            //创建一个新的协程并且立即启动执行
            var bitmapAsync = GlobalScope.async {
                logInstance("handlerOnClick2 step 4..")
                我要进行图片下载了(pictureUrl)
            }
            logInstance("handlerOnClick2 step 5..")
            //该方法表示，挂起当前协程,等async子协程执行完成之后恢复
            logInstance("handlerOnClick2 step 6..")
            //获取async子协程的结果
            val completed = bitmapAsync.await()
            logInstance("handlerOnClick2 step 7..")
            //切换协程到 主线程进行执行
            withContext(Dispatchers.Main) {
                val time2 = System.currentTimeMillis()
                tempImageView?.setImageBitmap(completed)
                logInstance("handlerOnClick2 step 8..")//日志8在主线程执行
                logInstance("time = ${time2 - time1}")
            }
            //上面的 withContext方法切换的现在.将会对后面的方法产生影响..所以日志9 也是在main线程进行执行的
            logInstance("handlerOnClick2 step 9..") //该日志9也在主线程执行
        }
        tempImageView = contentView.findViewById(R.id.showImg)
        logInstance("handlerOnClick2 step 10..")
    }
}