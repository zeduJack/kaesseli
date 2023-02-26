package ch.levelup.kaesseli.android


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import ch.levelup.kaesseli.state.Store
import ch.levelup.kaesseli.token.TokenActions
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FirebaseMessagingService : FirebaseMessagingService() {

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
    }

        // Check if message contains a notification payload.
        remoteMessage.notification?.let {
        Log.d(TAG, "Message Notification Body: ${it.body}")
            var notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            lateinit var notificationChannel : NotificationChannel
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel("1", "Generic Notifications", NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)
            }
            var notificationBuilder : NotificationCompat.Builder =
                NotificationCompat.Builder(applicationContext, "1")
            notificationBuilder.setSmallIcon(android.R.drawable.stat_notify_chat).setContentTitle("Neue Nachricht").setContentText(it.body).setAutoCancel(true)
            notificationManager.notify(1, notificationBuilder.build())
        }
    }

    /**
     * Called if the FCM registration token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the
     * FCM registration token is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        Store.instance.dispatch(TokenActions.SetToken(token))
    }


    /**
     * Persist token to server.
     *
     * Modify this method to associate the user's FCM registration token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        Log.d(TAG, "sendRegistrationTokenToServer($token)")
    }


    companion object {
        private const val TAG = "MyFirebaseMsgService"
    }
}