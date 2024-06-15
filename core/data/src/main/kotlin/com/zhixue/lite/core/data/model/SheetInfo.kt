package com.zhixue.lite.core.data.model

import com.zhixue.lite.core.database.model.SheetInfoEntity
import com.zhixue.lite.core.model.SheetPage
import com.zhixue.lite.core.network.model.NetworkSheetInfo
import kotlin.math.roundToInt

fun NetworkSheetInfo.asEntity(userId: String, paperId: String): SheetInfoEntity =
    SheetInfoEntity(
        userId = userId,
        paperId = paperId,
        userScore = data.value.answerRecord.list
            .filter { it.isSelected }
            .sumOf { it.score.toBigDecimal() }
            .toDouble(),
        standardScore = data.value.answerRecord.list
            .filter { it.isSelected }
            .sumOf { it.standardScore.toBigDecimal() }
            .toDouble(),
        pages = images.value.mapIndexed { index, image ->
            val location = data.value.location
            val page = location?.pages?.getOrNull(index)

            val (width, height) = when {
                // 已知页宽高
                page?.width != null && page.height != null -> page.width!!.roundToInt() to page.height!!.roundToInt()
                // 官方答题卡
                location?.comeFrom == 0 && location.paperType == "A3" -> 420 to 297
                location?.comeFrom == 0 && location.paperType == "A4" -> 210 to 297
                // 非官方答题卡
                location?.comeFrom == 1 && location.paperType == "A3" -> 2199 to 1555
                location?.comeFrom == 1 && location.paperType == "A4" -> 1100 to 1555
                // 未知格式
                else -> null to null
            }

            SheetPage(
                width = width,
                height = height,
                image = image,
                sections = page?.sections
                    ?.mapToSheetSections(userAnswerRecords = data.value.answerRecord.list)
                    ?: emptyList()
            )
        }
    )