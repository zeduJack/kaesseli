package ch.levelup.kaesseli

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.CoroutineContext

/**
 * A dispatchers for coroutines.  This will likely be removed once multithreaded coroutines
 * for Kotlin Native are supported.  For now each platform has an implementation.
 */
actual object PlatformDispatcher : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_main_queue()) {
            block.run()
        }
    }
}