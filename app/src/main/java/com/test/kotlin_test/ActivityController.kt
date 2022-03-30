package com.test.kotlin_test

import android.app.Activity

/**
 *
 * @Author : lpf
 * @CreateDate : 2022/2/14
 * @Description : 页面栈的管理
 */
object ActivityController {
    private val activitys=ArrayList<Activity>()

    fun addActivity(activity: Activity){
        activitys.add(activity)
    }

    fun removeActivity(activity: Activity){
        activitys.remove(activity)
    }

    fun finishAll(){
        for (activity in activitys){
            if (!activity.isFinishing){
                activity.finish()
            }
        }
        activitys.clear()
    }
}