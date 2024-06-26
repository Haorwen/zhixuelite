package com.zhixue.lite.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.zhixue.lite.core.model.TrendDirection

@Entity(
    tableName = "trend_info",
    primaryKeys = ["user_id", "code", "paper_id"]
)
data class TrendInfoEntity(
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "code")
    val code: String,
    @ColumnInfo(name = "paper_id")
    val paperId: String,
    @ColumnInfo(name = "paper_name")
    val paperName: String,
    @ColumnInfo(name = "datetime")
    val datetime: Long,
    @ColumnInfo(name = "level")
    val level: String,
    @ColumnInfo(name = "offset")
    val offset: Int,
    @ColumnInfo(name = "direction")
    val direction: TrendDirection,
    @ColumnInfo(name = "student_number")
    val studentNumber: Int
)