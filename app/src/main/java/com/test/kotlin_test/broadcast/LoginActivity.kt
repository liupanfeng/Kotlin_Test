package com.test.kotlin_test.broadcast

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.test.kotlin_test.BaseActivity
import com.test.kotlin_test.LunchActivity
import com.test.kotlin_test.R

class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onLogin(view:View){
        startActivity(Intent(this,LunchActivity::class.java))
    }
}
