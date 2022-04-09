package com.test.kotlin_test.coil

import android.os.Build.VERSION.SDK_INT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import coil.Coil
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.imageLoader
import coil.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.test.kotlin_test.R
import kotlinx.android.synthetic.main.activity_coil.*

/**
 * Kotlin 轻量级标配加载框架coil的使用
 */
class CoilActivity : AppCompatActivity() {


    private val imageUrl1:String="https://upload-images.jianshu.io/upload_images/27475960-eaf8f016dd1f591d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240";
    private val imageUrl2:String="https://upload-images.jianshu.io/upload_images/27475960-2a446f237613b685.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240";
    private val errorUrl:String="https://upload_images/auto-orient/2/error";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coil)
        //函数式编程思想
        // imageView.load(imageUrl1);  直接加载图片

        //imageView.load(R.mipmap.g4) //加载资源素材


//        imageView.load(imageUrl1){
//            crossfade(true)     //开启淡入淡出
//            crossfade(3000)
//            placeholder(R.mipmap.placeholder)   //添加占位图
//            transformations(CircleCropTransformation())  //图片变换  圆形
//        }

        //包含错误的情况
//        imageView.load(errorUrl){
//            crossfade(true)     //开启淡入淡出
//            crossfade(3000)
//            error(R.mipmap.error)   //加载失败的情况
//            transformations(CircleCropTransformation())  //图片变换
//        }


//        imageView.load(imageUrl1){
//            crossfade(true)     //开启淡入淡出
//            crossfade(3000)
//            placeholder(R.mipmap.placeholder)   //添加占位图
//            transformations(RoundedCornersTransformation(topRight = 30f, topLeft = 30f, bottomLeft = 30f, bottomRight = 30f))  //图片变换  加圆角
//        }

//        imageView.load(imageUrl2){
//            crossfade(true)     //开启淡入淡出
//            crossfade(3000)
//            //高斯模糊
//            transformations(BlurTransformation(context = applicationContext, radius = 5f, sampling = 5f))
//        }

//        imageView.load(imageUrl2){
//            crossfade(true)     //开启淡入淡出
//            crossfade(3000)
//            //灰度
//            transformations(GrayscaleTransformation())
//        }

        //加载gif
//        val imageLoader=ImageLoader.Builder(context = this)
//            .componentRegistry{
//                if (SDK_INT>28){
//                    add(ImageDecoderDecoder())
//                }else{
//                    add(GifDecoder())
//                }
//            }.build()
//        Coil.setImageLoader(imageLoader)
//        imageView.load("https://img-blog.csdnimg.cn/c271ed8cf0f541b08e1192d34e512448.gif")


        imageView.load(imageUrl2){
            crossfade(true)     //开启淡入淡出
            crossfade(3000)
            //灰度
            transformations(GrayscaleTransformation())
            listener(
                onStart ={ request ->
                    Log.d("lpf", "onError 开始加载...")
                },
                onError = { request, throwable ->
                    Log.d("lpf", "onError 加载失败...")
                },
                onCancel = { request -> // 这里应该是情况的意思
                    Log.d("lpf", "onCancel 加载重载中...")
                },
                onSuccess = { request, metadata ->
                    Log.d("lpf", "onSuccess 加载成功...")
                }

            )
        }

        //日志打印的顺序
//        2022-04-09 20:51:11.315 30169-30169/com.test.kotlin_test D/lpf: onCancel 加载重载中...
//        2022-04-09 20:51:11.332 30169-30169/com.test.kotlin_test D/lpf: onError 开始加载...
//        2022-04-09 20:51:14.427 30169-30169/com.test.kotlin_test D/lpf: onSuccess 加载成功...



    }
}