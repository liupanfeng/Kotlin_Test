package com.test.kotlin_test.network

import android.os.Bundle
import android.view.View
import com.test.kotlin_test.BaseActivity
import com.test.kotlin_test.R
import kotlinx.android.synthetic.main.activity_network.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class NetworkActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)
    }

//     fun sendRequest(view:View){
//        doRequestWithHttpURLConnection()
//    }
//
//    fun sendRequestWidthOkHttp(view:View){
//        doRequestWithOkHttp()
//    }
//
//    private fun doRequestWithHttpURLConnection(){
//        thread {
//            var connection:HttpURLConnection?=null
//            val response = StringBuilder()
//            val url = URL("https://www.baidu.com/")
//            connection = url.openConnection() as HttpURLConnection
//            connection.connectTimeout = 8000
//            connection.readTimeout = 8000
//            val inputStream = connection.inputStream
//            val reader = BufferedReader(InputStreamReader(inputStream))
//
//            reader.use {
//                reader.forEachLine {
//                    response.append(it)
//                }
//            }
//
//            showResponse( response.toString())
//
//        }
//    }
//
//
//    private fun doRequestWithOkHttp(){
//        thread {
//            val client=OkHttpClient()
//            val request=Request.Builder()
//                .url("https://www.baidu.com/")
//                .build()
//            val response=client.newCall(request).execute()
//            val string = response.body?.string()
//            showResponse(string)
//
//
//        }
//
//
//    }
//
//    private fun showResponse(content: String?) {
//        runOnUiThread {
//            responseText.text = content
//        }
//
//    }

}
