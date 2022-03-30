package com.test.kotlin_test.kotlin_senior

import android.os.Bundle
import android.util.Log
import com.test.kotlin_test.BaseActivity
import com.test.kotlin_test.R
import com.test.kotlin_test.letterCount

/**
 * 这个页面总结kotlin的高级语法
 */
class SeniorActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_senior)
        //扩展函数应用
        var test:String="asdf"
        Log.d("lpf","test="+test.letterCount())
    }




}
