package com.zhixue.lite.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.zhixue.lite.core.database.model.SheetInfoEntity

@Dao
interface SheetInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSheetInfo(entity: SheetInfoEntity)
}