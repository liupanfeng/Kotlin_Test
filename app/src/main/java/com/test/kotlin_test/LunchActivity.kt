package com.test.kotlin_test

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.test.kotlin_test.broadcast.BroadcastActivity
import com.test.kotlin_test.content_provider.ContentProviderActivity
import com.test.kotlin_test.fragment.FragmentActivity
import com.test.kotlin_test.kotlin_senior.SeniorActivity
import com.test.kotlin_test.materila.MaterialActivity
import com.test.kotlin_test.network.NetworkActivity
import com.test.kotlin_test.service.MainActivity
import com.test.kotlin_test.sqite.SaveDataActivity

/**
 * 页面启动
 */
class LunchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lunch)
    }

    fun startService(view:View){
        startActivity(Intent(this, MainActivity::class.java))
    }

    fun startMaterial(view: View){
        startActivity(Intent(this, MaterialActivity::class.java))
    }

    fun startBroadcast(view: View){
        startActivity(Intent(this, BroadcastActivity::class.java))
    }

    fun forceOffline(view:View){
        val intent=Intent("com.test.kotlin_test.FORCE_OFFLINE")
        sendBroadcast(intent)
    }

    fun startFragment(view:View){
        startActivity(Intent(this,FragmentActivity::class.java))
    }

    fun startContentProvider(view:View){
        startActivity(Intent(this,ContentProviderActivity::class.java))
    }


    fun startSaveData(view:View){
        startActivity(Intent(this,SaveDataActivity::class.java))
    }

    fun  kotlinSenior(view:View){
        startActivity(Intent(this,SeniorActivity::class.java))
    }

    fun  kotlinNetWork(view:View){
        startActivity(Intent(this, NetworkActivity::class.java))
    }


}
