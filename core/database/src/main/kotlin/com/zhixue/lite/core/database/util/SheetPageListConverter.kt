package com.zhixue.lite.core.database.util

import androidx.room.TypeConverter
import com.zhixue.lite.core.model.SheetPage
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal object SheetPageListConverter {
    @TypeConverter
    fun sheetPageListToJson(value: List<SheetPage>): String =
        Json.encodeToString(value)

    @TypeConverter
    fun jsonToSheetPageList(value: String): List<SheetPage> =
        Json.decodeFromString(value)
}