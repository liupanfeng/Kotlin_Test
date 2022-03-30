package com.test.kotlin_test.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.test.kotlin_test.BaseActivity
import com.test.kotlin_test.R
import com.test.kotlin_test.showToast

class BroadcastActivity : BaseActivity() {

    private lateinit var  receiver:TimeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)
        register()
    }


    fun sendBroadcast(view: View){
        val intent=Intent()
        intent.action = "com.test.kotlin_test.MY_RECEIVER"
        intent.setPackage("com.test.kotlin_test")
        sendBroadcast(intent)
    }

    fun sendOrBroadcast(view: View){
        val intent=Intent()
        intent.action = "com.test.kotlin_test.MY_RECEIVER"
        intent.setPackage("com.test.kotlin_test")
        sendOrderedBroadcast(intent,null)
    }




    /**
     * 动态注册广播
     */
    private fun register() {
        val intentFilter=IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        receiver=TimeChangeReceiver()
        registerReceiver(receiver,intentFilter)
    }


    inner class TimeChangeReceiver :BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
             showToast("time change")
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

}
