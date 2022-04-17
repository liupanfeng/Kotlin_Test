package com.test.kotlin_test.coroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.kotlin_test.coroutines.bean.ResponseWrapper
import com.test.kotlin_test.coroutines.bean.UserInfo
import androidx.lifecycle.viewModelScope
import com.test.kotlin_test.coroutines.repository.DataRepository
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

/**
 * * All rights reserved,Designed by www.meishesdk.com
 * @Author : lpf
 * @CreateDate : 2022/4/16 23:05
 * @Description : ViewModel 只负责LiveData的稳定性，不负责其他的任务  单一职责
 * @Copyright :www.meishesdk.com Inc.All rights reserved.
 */
class LoginViewModel:ViewModel() {

    var userInfo = MutableLiveData<ResponseWrapper<UserInfo>>()

    fun login(username:String,password:String){
        //GlobalScope 全局作用域
        //viewModelScope 默认的是主线程
        viewModelScope.launch{
            userInfo.value=DataRepository().login(username, password)
        }
    }
}