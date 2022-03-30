package com.test.kotlin_test.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.test.kotlin_test.R

class MyService : Service() {

    private val mBinder = DownloadBinder()

    override fun onBind(intent: Intent): IBinder {
        Log.d("MyService","onBind")
       return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("MyService","onCreate")

        val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            val channel=NotificationChannel("my_service","前台服务",NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        val intent=Intent(this, MainActivity::class.java)
        val pi=PendingIntent.getActivity(this,0,intent,0)
        val builder=NotificationCompat.Builder(this,"MyService")

        builder.setContentTitle("this is content title")
            .setContentInfo("this is content info")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setContentIntent(pi)


        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O) {
            builder.setChannelId("my_service")
        }
        val notification=builder.build()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MyService","onStartCommand")
//        stopSelf()
        return super.onStartCommand(intent, flags, startId)
        //执行到某个条件，可以调用这个方法自动停止Service

//        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyService","onDestroy")
    }

    class DownloadBinder:Binder(){
        private var i:Int=0
        fun startDownload(){
            Log.d("MyService","startDownload")
            i++
        }

        fun getProgress():Int{
            Log.d("MyService","getProgress = "+i)
            return i
        }
    }


}
