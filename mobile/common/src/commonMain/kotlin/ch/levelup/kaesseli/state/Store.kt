package ch.levelup.kaesseli.state

import org.reduxkotlin.applyMiddleware
import org.reduxkotlin.createThreadSafeStore
import org.reduxkotlin.createThunkMiddleware

object Store {
    val instance = createThreadSafeStore(
        ::rootReducer,
        AppState(),
        applyMiddleware(
            createThunkMiddleware()
        )
    )
}