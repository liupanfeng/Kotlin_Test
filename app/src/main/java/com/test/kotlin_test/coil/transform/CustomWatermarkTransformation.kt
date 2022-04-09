package com.derry.coil_project.ktstudy.transform

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.annotation.ColorInt
import coil.bitmap.BitmapPool
import coil.size.Size
import coil.transform.BlurTransformation
import coil.transform.Transformation

/**
 * 给图片加水印
 *  为图片添加水印的思路也很简单，只需要对 canvas 稍微坐下旋转，然后绘制文本即可
 */
class CustomWatermarkTransformation(
    private val watermark: String, // 添加水印的文本内容
    @ColorInt private val textColor: Int, // 添加水印的颜色值
    private val textSize: Float // 添加水印的文本内容的字体大小
    ) : Transformation {

    // 此方法的返回值是用于计算图片在内存缓存中的唯一 Key 时的辅助参数，所以需要实现该方法，为 Transformation 生成一个可以唯一标识自身的字符串 Key。
    override fun key(): String = "${CustomWatermarkTransformation::class.java.name}-${watermark}-${textColor}-${textSize}"

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

        canvas.drawBitmap(input, 0f, 0f, paint) // 画出 本来的(当前) 图层

        // TODO ===================== 下面就是水印区域
        canvas.rotate(40f, width / 2f, height / 2f) // 然后再 文字 按中心点 旋转

        paint.textSize = textSize // 画笔文字大小
        paint.color = textColor // 画笔颜色

        val textWidth = paint.measureText(watermark) // 测量文字内容的宽度

        canvas.drawText(watermark, (width - textWidth) / 2f, height / 2f, paint) // 绘制到中间

        return output // 返回最终的成果
    }
}