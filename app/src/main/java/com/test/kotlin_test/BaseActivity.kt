package com.test.kotlin_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.test.kotlin_test.broadcast.LoginActivity

/**
 *
 * @Author : lpf
 * @CreateDate : 2022/2/14
 * @Description :
 */
open class BaseActivity :AppCompatActivity() {

    private lateinit var receiver:ForceOfflineReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityController.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityController.removeActivity(this)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter=IntentFilter()
        intentFilter.addAction("com.test.kotlin_test.FORCE_OFFLINE")
        receiver=ForceOfflineReceiver()
        registerReceiver(receiver,intentFilter)
    }


    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    inner class ForceOfflineReceiver :BroadcastReceiver(){
        override fun onReceive(context: Context, intent: Intent?) {
            AlertDialog.Builder(context).apply {
                setTitle("注意")
                setMessage("你正在操作强制退出登录")
                setCancelable(false)
                setPositiveButton("ok"){_,_->
                    ActivityController.finishAll() //销毁所有的Activity
                    val intent=Intent(context,LoginActivity::class.java)
                    context.startActivity(intent)
                }
                show()
            }
        }

    }
}