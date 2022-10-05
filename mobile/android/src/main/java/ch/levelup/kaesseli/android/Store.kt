package ch.levelup.kaesseli.android

import ch.levelup.kaesseli.state.AppState
import ch.levelup.kaesseli.state.rootReducer
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