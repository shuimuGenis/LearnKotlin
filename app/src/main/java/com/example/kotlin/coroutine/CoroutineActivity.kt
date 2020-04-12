package com.example.kotlin.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlin.R
import kotlinx.android.synthetic.main.activity_corouting.*

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
        val coroutines2 = TestCoroutines2(this, pictureUrl)
        //使用kotlinx轻量级协程框架
        dowloadImgBtn.setOnClickListener {
            coroutines2.handlerOnClick2()
        }
    }
}