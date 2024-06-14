package com.zhixue.lite.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import com.zhixue.lite.core.model.PaperInfo
import com.zhixue.lite.core.model.TrendDirection

data class PopulatedPaperInfo(
    @Embedded
    val paperInfoEntity: PaperInfoEntity,
    @ColumnInfo(name = "level")
    val level: String?,
    @ColumnInfo(name = "direction")
    val direction: TrendDirection?,
    @ColumnInfo(name = "student_number")
    val studentNumber: Int?
)

fun PopulatedPaperInfo.asExternalModel(): PaperInfo =
    PaperInfo(
        id = paperInfoEntity.id,
        subjectName = paperInfoEntity.subjectName,
        userScore = paperInfoEntity.userScore,
        standardScore = paperInfoEntity.standardScore,
        scoreRate = paperInfoEntity.scoreRate,
        classRank = paperInfoEntity.classRank,
        classPercentile = paperInfoEntity.classPercentile,
        level = level,
        direction = direction,
        studentNumber = studentNumber
    )