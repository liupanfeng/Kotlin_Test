package com.derry.coil_project.ktstudy.transform

import android.graphics.*
import androidx.annotation.ColorInt
import coil.bitmap.BitmapPool
import coil.size.Size
import coil.transform.BlurTransformation
import coil.transform.Transformation

/**
 *  给图片加蒙层
 */
class CustomColorFilterTransformation(
    @ColorInt private val color: Int // 给图片加蒙层 的 颜色值
) : Transformation {

    // 此方法的返回值是用于计算图片在内存缓存中的唯一 Key 时的辅助参数，所以需要实现该方法，为 Transformation 生成一个可以唯一标识自身的字符串 Key。
    override fun key(): String = "${CustomColorFilterTransformation::class.java.name}-$color"

    // 此方法包含了一个 BitmapPool 参数，我们在实现图形变换的时候往往是需要一个全新的 Bitmap，此时就应该通过 BitmapPool 来获取，尽量复用已有的 Bitmap
    override suspend fun transform(pool: BitmapPool, input: Bitmap, size: Size): Bitmap {
        val width = input.width // 获取当前图层的宽信息
        val height = input.height // 获取当前图层的高信息
        val config = input.config // 获取当前图层的配置信息

        // 通过上面的 宽高配置 等 信息，向 复用池里面去寻得 最合适 能够复用的 Bitmap（注意：此Bitmap也是增加一层图层的成果）
        val output = pool.get(width, height, config)

        val canvas = Canvas(output) // 构建画布
        val paint = Paint() // 构建画笔
        paint.isAntiAlias = true // 画笔的抗锯齿

        // 同学们不懂的话，看此文章：https://www.twle.cn/l/yufei/android/android-basic-porterduffcolorfilter.html
        // 颜色混合滤镜  参数一：颜色    参数二：模式(丢弃不覆盖目标像素的源像素。在目标像素上绘制剩余的源像素。)
        paint.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_ATOP)

        canvas.drawBitmap(input, 0f, 0f, paint) // 目的就是把 PorterDuffColorFilter 绘制上去 变成蒙层

        return output  // 返回最终的成果
    }
}