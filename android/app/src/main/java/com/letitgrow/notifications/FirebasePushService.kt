// LET-26: Firebase Cloud Messaging setup for Android
// android/app/src/main/java/com/letitgrow/notifications/FirebasePushService.kt

package com.letitgrow.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.letitgrow.MainActivity
import com.letitgrow.R

/**
 * Firebase Cloud Messaging Service für LetItGrow App
 * Behandelt Push-Benachrichtigungen für Pflegeerinnerungen
 * 
 * Verknüpft mit Jira Task: LET-26
 * Author: Sebastian (LetItGrow Team)
 * Branch: feature/LET-26-firebase-push-android
 */
class FirebasePushService : FirebaseMessagingService() {

    companion object {
        private const val CHANNEL_ID = "letitgrow_care_notifications"
        private const val CHANNEL_NAME = "Pflege-Erinnerungen"
        private const val NOTIFICATION_ID = 100
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    /**
     * LET-26: Wird aufgerufen wenn ein neues Token generiert wird
     * Token wird an Backend gesendet für Push-Targeting
     */
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        
        // LET-26: Token ans Backend senden
        sendTokenToServer(token)
        
        // Lokal speichern für Fallback
        saveTokenLocally(token)
    }

    /**
     * LET-26: Empfang von Push-Nachrichten
     * Behandelt verschiedene Nachrichtentypen (Gießen, Düngen, Community)
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Das Feuer muss leuchten bei den Usern einen Funken erzeugen
        val title = remoteMessage.notification?.title ?: "🌿 LetItGrow"
        val body = remoteMessage.notification?.body ?: ""
        val plantId = remoteMessage.data["plant_id"]
        val actionType = remoteMessage.data["action_type"] // water, fertilize, community

        // LET-26: Notification mit Deep-Link erstellen
        createNotification(title, body, plantId, actionType)
    }

    private fun createNotification(
        title: String, 
        body: String, 
        plantId: String?, 
        actionType: String?
    ) {
        // Deep-Link Intent für spezifische Pflanzen-View
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            
            // Deep-Link Parameter für Navigation
            plantId?.let { putExtra("plant_id", it) }
            actionType?.let { putExtra("action_type", it) }
            putExtra("from_notification", true)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // Notification mit LetItGrow Branding und emotionaler Ansprache
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_plant_notification)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setColor(getColor(R.color.letitgrow_green))
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Benachrichtigungen für Pflanzenpflege und Community-Updates"
                enableLights(true)
                lightColor = getColor(R.color.letitgrow_green)
                enableVibration(true)
            }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendTokenToServer(token: String) {
        // TODO: LET-26 - Backend API Integration
        // POST /api/notifications/token
        // Headers: Authorization: Bearer <user_token>
        // Body: { "fcm_token": token, "device_type": "android" }
        
        println("LET-26: Sending FCM token to backend: $token")
    }

    private fun saveTokenLocally(token: String) {
        val sharedPref = getSharedPreferences("letitgrow_prefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("fcm_token", token)
            putLong("token_timestamp", System.currentTimeMillis())
            apply()
        }
    }
}
