package com.example.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class MyForeGroundService : Service() {

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val channelID =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotifChannel(this)
        } else {
            ""
        }

        val channelBuilder = NotificationCompat.Builder(this, channelID)

        val notification = channelBuilder.setOngoing(true)
            .setSmallIcon(R.drawable.ic_baseline_fastfood_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(Notification.CATEGORY_SERVICE)
            .setContentTitle("Fore Ground Service Started")
            .setContentText("Service Running...")
            .build()

        startForeground(101, notification)

        return START_STICKY
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotifChannel(context: Context): String {

        val channelID = "my_service"
        val channelName = "my foreground service"

            val channel = NotificationChannel(
                channelID,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )

            channel.apply {
                lightColor = Color.BLUE
                importance = NotificationManager.IMPORTANCE_HIGH
                lockscreenVisibility = Notification.VISIBILITY_PRIVATE
            }

            val service = ContextCompat
                .getSystemService(context, NotificationManager::class.java) as NotificationManager
//                .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            service.createNotificationChannel(channel)


        return channelID

    }

    override fun onDestroy() {
        super.onDestroy()
    }



}