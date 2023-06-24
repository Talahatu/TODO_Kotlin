package com.example.todo_kpb.util

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.todo_kpb.R
import com.example.todo_kpb.view.MainActivity

class NotificationHelper(val contextM: Context) {
    private val CHANNEL = "todo_channel_id"
    private val NOTIFICATION = 1

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL,CHANNEL,NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "Todo channel description"
            }
            val notifManager = contextM.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notifManager.createNotificationChannel(channel)
        }
    }
    @SuppressLint("MissingPermission")
    fun createNotification(title:String, message:String){
        createNotificationChannel()
        val intent = Intent(contextM,MainActivity::class.java).apply {
            flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(contextM,0,intent,0)
        val icon = BitmapFactory.decodeResource(contextM.resources, R.drawable.todochar)
        val notif = NotificationCompat.Builder(contextM,CHANNEL)
            .setSmallIcon(R.drawable.checklist)
            .setLargeIcon(icon)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(icon)
                    .bigLargeIcon(null))
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        NotificationManagerCompat.from(contextM).notify(NOTIFICATION,notif)
    }
}