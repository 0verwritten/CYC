package com.example.specialforaleg20edition

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

@SuppressLint("WrongConstant", "UnspecifiedImmutableFlag")
fun NotificationManager.sendNotification(title: String, messageBody: String, channelName: String, applicationContext: Context) {

    val NotificationID = (1..1000).random();

    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NotificationID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val builder = NotificationCompat.Builder(
        applicationContext,
        "noto_channel"
    ).setSmallIcon(R.drawable.ic_launcher_background)
        .setContentTitle(
            title
        )
        .setContentText(messageBody)
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
    notify(NotificationID, builder.build())
}

fun NotificationManager.cancelNotifications() {
    cancelAll()
}