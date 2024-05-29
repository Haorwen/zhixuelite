package com.zhixue.lite.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zhixue.lite.core.model.ReportInfo

@Entity(tableName = "report_info")
data class ReportInfoEntity(
    @PrimaryKey
    @ColumnInfo(name = "report_id")
    val reportId: String,
    @ColumnInfo(name = "report_type")
    val reportType: String,
    @ColumnInfo(name = "report_name")
    val reportName: String,
    @ColumnInfo(name = "date_time")
    val dateTime: Long,
    @ColumnInfo(name = "is_published")
    val isPublished: Boolean
)

fun ReportInfoEntity.asExternalModel(): ReportInfo = ReportInfo(
    reportId = reportId,
    reportName = reportName,
    dateTime = dateTime,
    isPublished = isPublished
)