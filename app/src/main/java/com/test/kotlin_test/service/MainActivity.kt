package com.test.kotlin_test.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import com.test.kotlin_test.R
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var downloadBinder: MyService.DownloadBinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startMyThread(){
        //这样就可以，不需要写start
        thread{
            Log.d("lpf","start thread")
        }
    }

    private val connection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d("MyService","onServiceDisconnected")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d("MyService","onServiceConnected")
            downloadBinder=service as MyService.DownloadBinder
            downloadBinder.startDownload()
            downloadBinder.getProgress()
        }

    }

    fun onBind(view:View){
        var intent=Intent(this, MyService::class.java)
        bindService(intent,connection, Context.BIND_AUTO_CREATE)

    }

    fun startService(view:View){
        val intent = Intent(this, MyService::class.java)
        startService(intent)
    }

    fun stopService(view:View){
        val intent = Intent(this, MyService::class.java)
        stopService(intent)
    }


    fun unBind(view:View){
        unbindService(connection)
    }


}
