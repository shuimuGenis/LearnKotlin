package com.example.kotlin.coroutine

import com.example.kotlin.logInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.android.HandlerDispatcher
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

/**
 * @author shuimu{lwp}
 * @time 2019/8/14  11:59
 * @desc
 */
class BaseContinuation(ui: MainCoroutineDispatcher) : Continuation<Unit> {
    override val context: CoroutineContext = ui

    override fun resumeWith(result: Result<Unit>) {
        if (result.isFailure) {
            logInstance(result.toString())
        }
    }
}