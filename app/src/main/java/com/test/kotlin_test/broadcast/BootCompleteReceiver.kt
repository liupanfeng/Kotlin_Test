package com.test.kotlin_test.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.test.kotlin_test.showToast

class BootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        showToast("reboot already")
    }
}
