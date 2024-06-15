package com.zhixue.lite.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zhixue.lite.core.database.model.SheetInfoEntity

@Dao
interface SheetInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSheetInfo(entity: SheetInfoEntity)

    @Query(
        """
            SELECT * FROM sheet_info
            WHERE user_id = :userId AND paper_id = :paperId
        """
    )
    suspend fun getSheetInfo(userId: String, paperId: String): SheetInfoEntity
}