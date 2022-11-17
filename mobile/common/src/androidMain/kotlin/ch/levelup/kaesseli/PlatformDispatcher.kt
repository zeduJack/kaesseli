package ch.levelup.kaesseli

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Runnable
import kotlin.coroutines.CoroutineContext

actual object PlatformDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        // todo: check if its a good idea to use here the main dispatcher
        Dispatchers.Main.dispatch(context, block)
    }
}