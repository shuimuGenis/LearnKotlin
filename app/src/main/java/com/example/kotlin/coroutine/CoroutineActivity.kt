package com.example.kotlin.coroutine

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.example.kotlin.R
import com.example.kotlin.logInstance
import kotlinx.android.synthetic.main.activity_corouting.*
import kotlinx.coroutines.*
import kotlinx.coroutines.android.Main

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc
 */
class CoroutineActivity : AppCompatActivity() {
    //随意找的一个百度图片
    private val pictureUrl: String =
        "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1583691827504&di=1af7592dd4f478667e9f9fbc2eb5cc88&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2Fc7c6dfeb7cbf658825a6411535b18ceba15278e1.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_corouting)
        /*dowloadImgBtn.setOnClickListener {
            logInstance("点击事件开始了")
            我要开始协程了 {
                logInstance("协程开始了")
                var tempImg = findViewById<ImageView>(R.id.showImg)
                var requestBitmap = 开始下载图片(pictureUrl)
                tempImg.setImageBitmap(requestBitmap)
                logInstance("协程结束了")
            }
            logInstance("点击事件结束了")
        }*/

        //使用自己定义的协程框架
        /*  dowloadImgBtn.setOnClickListener {
              logInstance("click start..")
              我要开始协程了 {
                  logInstance("coroutine one..")
                  val tempImageView = findViewById<ImageView>(R.id.showImg)
                  var requestBitmap = 我要开始异步了 {
                      我要进行图片下载了(pictureUrl)
                  }
                  logInstance("coroutine two..")
                  tempImageView.setImageBitmap(requestBitmap)
                  logInstance("coroutine three..")
              }
              logInstance("click end..")
          }*/

        //使用kotlinx轻量级协程框架
        dowloadImgBtn.setOnClickListener {
            handlerOnClick2()
        }
    }

    fun handlerOnClick() {
        val time1 = System.currentTimeMillis()
        logInstance("handlerOnClick2 step 1..")
        //指定协程在当前的线程执行
        GlobalScope.launch(Dispatchers.Main) {
            logInstance("handlerOnClick2 step 2..")
            val tempImageView = findViewById<ImageView>(R.id.showImg)
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
            logInstance("time = ${time2-time1}")
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
            val tempImageView = findViewById<ImageView>(R.id.showImg)
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
                logInstance("time = ${time2-time1}")
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
                logInstance("time = ${time2-time1}")
            }
            //上面的 withContext方法切换的现在.将会对后面的方法产生影响..所以日志9 也是在main线程进行执行的
            logInstance("handlerOnClick2 step 9..") //该日志9也在主线程执行
        }
        tempImageView = findViewById(R.id.showImg)
        logInstance("handlerOnClick2 step 10..")
    }
}