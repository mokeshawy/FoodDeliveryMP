package org.saham.fooddelivery.core.background_services


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.saham.fooddelivery.R


private const val NOTIFICATION_CHANNEL_ID = "UpdateLocationChannel"
private const val NOTIFICATION_ID = 1
private const val NOTIFICATION_NAME = "Location Update"

class LocationLifecycleService : LifecycleService() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val notification = createNotification()
        startForeground(NOTIFICATION_ID, notification)
        startLocationUpdates()
        return START_STICKY
    }

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Location Update")
            .setContentText("Sending location updates")
            .setSmallIcon(R.drawable.ic_vector_location)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_NAME,
                NotificationManager.IMPORTANCE_LOW
            )
            getSystemService(NotificationManager::class.java)?.createNotificationChannel(channel)
        }
    }


    private var updateLocationJop: Job? = null
    private fun startLocationUpdates() {
        updateLocationJop?.cancel()
        updateLocationJop = lifecycleScope.launch {
            while (isActive) {
               //TODO IMPLEMENT UPDATE LOCATION REQUEST HERE
                delay(30000)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d("LocationService", "LifecycleService destroyed")
        updateLocationJop?.cancel()
    }
}
