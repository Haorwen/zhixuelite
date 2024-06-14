package com.zhixue.lite.core.data.model

import com.zhixue.lite.core.model.SheetSection
import com.zhixue.lite.core.network.model.NetworkSheetSection
import com.zhixue.lite.core.network.model.NetworkAnswerRecordInfo

fun List<NetworkSheetSection>.mapToSheetSections(
    userAnswerRecords: List<NetworkAnswerRecordInfo>
): List<SheetSection> = map { section ->
    val indexes = section.contents.branches.flatMap { it.indexes }
    SheetSection(
        x = section.contents.position.x,
        y = section.contents.position.y,
        width = section.contents.position.width,
        score = indexes
            .sumOf { userAnswerRecords[it - 1].score.toBigDecimal() }
            .toDouble(),
        standardScore = indexes
            .sumOf { userAnswerRecords[it - 1].standardScore.toBigDecimal() }
            .toDouble()
    )
}