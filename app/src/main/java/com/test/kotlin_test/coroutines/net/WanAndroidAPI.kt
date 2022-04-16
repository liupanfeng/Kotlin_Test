package com.test.kotlin_test.coroutines.net

import com.test.kotlin_test.coroutines.bean.ResponseWrapper
import com.test.kotlin_test.coroutines.bean.UserInfo
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * * All rights reserved,Designed by www.meishesdk.com
 * @Author : lpf
 * @CreateDate : 2022/4/16 22:48
 * @Description : 使用开源的接口地址
 * @Copyright :www.meishesdk.com Inc.All rights reserved.
 */
interface WanAndroidAPI {

    @POST("/user/login")
    @FormUrlEncoded
   suspend fun login(@Field("username") username:String,
              @Field("password") password:String):ResponseWrapper<UserInfo>
}