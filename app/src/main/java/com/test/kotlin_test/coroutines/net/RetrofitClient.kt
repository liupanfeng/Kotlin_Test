package com.test.kotlin_test.coroutines.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * * All rights reserved,Designed by www.meishesdk.com
 * @Author : lpf
 * @CreateDate : 2022/4/16 22:31
 * @Description : 初始化Retrofit
 * @Copyright :www.meishesdk.com Inc.All rights reserved.
 */
class RetrofitClient {

    private object Holder{
        val INSTANCE = RetrofitClient();
    }

    companion object{//派生
        val  instance= Holder.INSTANCE
        private const val BASE_URL:String="https://www.wanandroid.com"
    }

    fun <T> initRetrofit(apiInterface:Class<T>):T{
        val mOkHttpClient=OkHttpClient().newBuilder().myApply {
            readTimeout(10000,TimeUnit.SECONDS)
            connectTimeout(10000,TimeUnit.SECONDS)
            writeTimeout(10000,TimeUnit.SECONDS)
        }.build()

        val retrofit:Retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(apiInterface)

    }

    fun <T> T.myApply(mm:T.()->Unit):T{
        mm()
        return this;
    }
}