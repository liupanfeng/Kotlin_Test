package com.test.kotlin_test.coroutines.repository

import com.test.kotlin_test.coroutines.bean.ResponseWrapper
import com.test.kotlin_test.coroutines.bean.UserInfo
import com.test.kotlin_test.coroutines.net.RetrofitClient
import com.test.kotlin_test.coroutines.net.WanAndroidAPI

/**
 * * All rights reserved,Designed by www.meishesdk.com
 * @Author : lpf
 * @CreateDate : 2022/4/16 22:53
 * @Description : 数据仓库
 * @Copyright :www.meishesdk.com Inc.All rights reserved.
 */
class DataRepository {

    suspend fun login(username:String,password:String): ResponseWrapper<UserInfo>? {
        if (!username.isNullOrEmpty() and !password.isNullOrEmpty()){
            val login = RetrofitClient.instance.initRetrofit(WanAndroidAPI::class.java)
                .login(username, password);
            return login
        }
        return null
    }
}