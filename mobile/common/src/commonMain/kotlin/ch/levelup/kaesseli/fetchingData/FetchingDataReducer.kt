package ch.levelup.kaesseli.fetchingData


fun fetchinDatakReducer(state: Boolean, action: Any) =
    when (action) {
        is FetchingDataActions.StartNetworkRequest -> true
        is FetchingDataActions.EndNetworkRequest -> false
        else -> state
    }
