package com.learn.kotlin

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    var innerHandler: Handler = InnerHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    class InnerHandler(var context: Context) : Handler() {
        override fun handleMessage(msg: Message) {
            Toast.makeText(context, "msg :${msg.data?.getString("data")}", Toast.LENGTH_SHORT)
                .show()
        }
    }

    /**
     * 主线程发生Handler消息
     */
    fun onSendMsgOnMain(view: View) {
        innerHandler.sendMessage(
            Message.obtain().apply { data = Bundle().apply { putString("data", "来自主线程的Json数据") } })
    }

    var lock = ReentrantLock()
    var thread: Thread? = null
    var condition: Condition = lock.newCondition()

    @Volatile
    var isStop = false

    /**
     * 子线程发生Handler消息，消息处理在主线程
     */
    fun onSendMsgOnThread(view: View) {
        lock.lock()
        if (lock.hasWaiters(condition)) {
            condition.signal()
        }
        lock.unlock()
        if (thread == null) {
            thread = thread {
                try {
                    lock.lock()
                    val threadHandler = Handler(Looper.getMainLooper(), Handler.Callback {
                        Toast.makeText(
                            this,
                            "current Thread is ${Thread.currentThread().name}  msg :${it.data?.getString(
                                "data"
                            )}",
                            Toast.LENGTH_SHORT
                        ).show()
                        true
                    })
                    val bunde=Bundle().apply { putString("data", "来自${Thread.currentThread().name}线程的Json数据") }
                    while (!isStop) {
                        threadHandler.sendMessage(Message.obtain().apply { data =bunde })
                        condition.await()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    if (lock.isHeldByCurrentThread) {
                        lock.unlock()
                    }
                }
            }
        }
    }

    fun stopSendMsgThread(view: View) {
        isStop = true
    }

    /**
     * 显示键盘
     */
    fun showKeyBroad(view: View) {
        var imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    /**
     * 隐藏键盘
     */
    fun hideKeyBroad(view: View) {
        var imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}