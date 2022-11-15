package ch.levelup.kaesseli.state

import ch.levelup.kaesseli.errorMessage.errorMessageReducer
import ch.levelup.kaesseli.fetchingData.fetchinDatakReducer
import ch.levelup.kaesseli.navigation.navigationReducer
import ch.levelup.kaesseli.user.userReducer
import ch.levelup.kaesseli.userRegistration.userRegistrationReducer
import ch.levelup.kaesseli.welcomeMessage.welcomeMessageReducer

fun rootReducer(state: AppState, action: Any) = AppState(
    navigation = navigationReducer(state.navigation, action),
    welcomeMessage = welcomeMessageReducer(state.welcomeMessage, action),
    fetchingData = fetchinDatakReducer(state.fetchingData, action),
    errorMessage = errorMessageReducer(state.errorMessage, action),
    userRegistration = userRegistrationReducer(state.userRegistration, action),
    user = userReducer(state.user, action),
    stringUser = state.stringUser.let {
        when (action) {
            is SetString -> action.user
            else -> it
        }
    },
    countries = state.countries.let {
        when (action) {
            is AddCountry -> it + "new entry"
            else -> it
        }
    }
)