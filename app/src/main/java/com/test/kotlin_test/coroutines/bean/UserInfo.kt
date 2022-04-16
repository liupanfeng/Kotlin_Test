package com.test.kotlin_test.coroutines.bean

/**
 * * All rights reserved,Designed by www.meishesdk.com
 * @Author : lpf
 * @CreateDate : 2022/4/16 22:52
 * @Description : koltin 数据bean
 * @Copyright :www.meishesdk.com Inc.All rights reserved.
 */
data class UserInfo(
    val admin: Boolean,
    val chapterTops: List<*>,
    val collectIds: List<*>,
    val email: String ?,
    val icon: String?,
    val id: String?,
    val nickname: String?,
    val password: String?,
    val publicName: String?,
    val token: String?,
    val type: Int,
    val username: String?
)
