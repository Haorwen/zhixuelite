package com.zhixue.lite.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.zhixue.lite.core.model.SheetPage

@Entity(
    tableName = "sheet_info",
    primaryKeys = ["user_id", "paper_id"]
)
data class SheetInfoEntity(
    @ColumnInfo(name = "user_id")
    val userId: String,
    @ColumnInfo(name = "paper_id")
    val paperId: String,
    @ColumnInfo(name = "pages")
    val pages: List<SheetPage>
)