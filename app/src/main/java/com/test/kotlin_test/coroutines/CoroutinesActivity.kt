package com.test.kotlin_test.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.test.kotlin_test.R
import com.test.kotlin_test.coroutines.viewmodel.LoginViewModel
import com.test.kotlin_test.databinding.ActivityCoroutinesBinding
import kotlinx.android.synthetic.main.activity_coroutines.*

class CoroutinesActivity : AppCompatActivity() {
    lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //绑定DataBinding
        val dataBinding= DataBindingUtil.setContentView<ActivityCoroutinesBinding>(this,R.layout.activity_coroutines)
        dataBinding.lifecycleOwner=this

        viewModel= ViewModelProvider(this, ViewModelProvider.
        NewInstanceFactory()).get(LoginViewModel::class.java)

        dataBinding.loginViewModel=viewModel

    }

    //{
    //    "data": {
    //        "admin": false,
    //        "chapterTops": [],
    //        "coinCount": 0,
    //        "collectIds": [],
    //        "email": "",
    //        "icon": "",
    //        "id": 129892,
    //        "nickname": "lpf666",
    //        "password": "",
    //        "publicName": "lpf666",
    //        "token": "",
    //        "type": 0,
    //        "username": "lpf666"
    //    },
    //    "errorCode": 0,
    //    "errorMsg": ""
    //}

    //username =lpf666 password=123456
    fun onLogin(view: View){
//        viewModel.login(username.text.toString(),password.text.toString())
        viewModel.login("lpf666","123456")
    }
}
