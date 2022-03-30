package com.test.kotlin_test

/**
 *
 * @Author : lpf
 * @CreateDate : 2022/2/24
 * @Description :
 */

//定义扩展函数的定义
//    fun ClassName.methodName(param1:Int,param2:Int):Int{
//        return 0
//    }

//这样就在String类上面定义了一个函数
fun String.letterCount():Int{
    var count=0
    for(char in this){
        if (char.isLetter()){
            count++
        }
    }
    return count
}