package com.zhixue.lite.core.database.model

import androidx.room.ColumnInfo
import com.zhixue.lite.core.model.ScoreInfo

data class ReportScoreInfo(
    @ColumnInfo("user_score")
    val userScore: Double,
    @ColumnInfo("standard_score")
    val standardScore: Double
)

fun ReportScoreInfo.asExternalModel(): ScoreInfo = ScoreInfo(
    userScore = userScore,
    standardScore = standardScore
)