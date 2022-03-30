package com.test.kotlin_test

import android.widget.Toast

/**
 *
 * @Author : lpf
 * @CreateDate : 2022/2/12
 * @Description :定义顶层函数
 */
fun showToast(content:String){
    Toast.makeText(App.mContext,content,Toast.LENGTH_LONG).show()
}