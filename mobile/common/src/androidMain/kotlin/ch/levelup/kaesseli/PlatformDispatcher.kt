package ch.levelup.kaesseli

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlin.coroutines.CoroutineContext

actual object PlatformDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        Dispatchers.IO.dispatch(context, block)
    }
}