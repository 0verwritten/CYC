package com.example.specialforaleg20edition

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.vpMiddle)
        viewPager.adapter = PageAdapter(supportFragmentManager)

        val tabLayout = findViewById<TabLayout>(R.id.tlBottom)
        tabLayout.setupWithViewPager(viewPager)

        tlBottom.getTabAt(0)!!.setIcon(R.drawable.ic_home)
        tlBottom.getTabAt(1)!!.setIcon(R.drawable.ic_add)
        tlBottom.getTabAt(2)!!.setIcon(R.drawable.ic_shop_list)
        tlBottom.getTabAt(3)!!.setIcon(R.drawable.ic_diet)


    }
    override fun onPause() {
        val tips = resources.getStringArray(R.array.Tips)
        val pos:Int = (0..4).random()

        Log.e("Nice", "It's working")
        sendNotification(this,tips[pos], tips[pos + 5])
        super.onPause()
    }

    private fun sendNotification(context: MainActivity, title: String, description: String){
        lateinit var notificationChannel: NotificationChannel


        var notificationManager: NotificationManager =
            ContextCompat.getSystemService(context, NotificationManager::class.java) as NotificationManager
        notificationManager.cancelAll();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(
                "noto_channel",
                "Important deadlines",
                NotificationManager.IMPORTANCE_HIGH
            )
                .apply {
                    setShowBadge(false)
                }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "descriptio"
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager


        notificationManager.sendNotification(
            title,
            description,
            "Important deadlines",
            context
        )
        Log.e("Nice", "Another working")}
}