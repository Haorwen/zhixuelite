package com.zhixue.lite.feature.paper.util

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.graphics.applyCanvas
import androidx.core.graphics.createBitmap
import coil.size.Size
import coil.transform.Transformation
import com.zhixue.lite.core.model.SheetSection

internal class SheetImageTransformation(
    private val width: Int?,
    private val height: Int?,
    private val sections: List<SheetSection>
) : Transformation {

    override val cacheKey: String = javaClass.name

    override suspend fun transform(input: Bitmap, size: Size): Bitmap {
        return if (width != null && height != null) {
            val widthScale = input.width.toFloat() / width
            val heightScale = input.height.toFloat() / height

            createBitmap(input.width, input.height)
                .applyCanvas {
                    // 绘制试卷图片
                    drawBitmap(input, 0f, 0f, null)
                    // 绘制Section分数文本
                    val textPaint = Paint().apply {
                        color = Color.RED
                        textSize = input.width.toFloat() / 60
                        isFakeBoldText = true
                    }
                    val textRect = Rect()
                    sections.forEach { section ->
                        val scoreText = "${section.score} / ${section.standardScore}"
                        // 获取文字所在矩形区域
                        textPaint.getTextBounds(scoreText, 0, scoreText.length, textRect)
                        // 绘制文字
                        drawText(
                            scoreText,
                            (section.x + section.width) * widthScale - textRect.width(),
                            section.y * heightScale + textRect.height(),
                            textPaint
                        )
                    }
                }
        } else {
            input
        }
    }
}