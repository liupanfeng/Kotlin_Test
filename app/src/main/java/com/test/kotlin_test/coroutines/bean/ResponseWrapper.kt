package com.test.kotlin_test.coroutines.bean

/**
 * * All rights reserved,Designed by www.meishesdk.com
 * @Author : lpf
 * @CreateDate : 2022/4/16 22:55
 * @Description : 接口返回值包装类
 * @Copyright :www.meishesdk.com Inc.All rights reserved.
 */
data class ResponseWrapper<T>(
    val data: T, val errorCode: Int, val errorMsg: String
)
