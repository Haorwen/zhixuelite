package com.zhixue.lite.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.RoomWarnings
import androidx.room.Transaction
import com.zhixue.lite.core.database.model.PaperInfoEntity
import com.zhixue.lite.core.database.model.PopulatedPaperInfo

@Dao
interface PaperInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaperInfoList(entities: List<PaperInfoEntity>)

    @Query(
        """
            SELECT id FROM paper_info
            WHERE user_id = :userId AND report_id = :reportId
        """
    )
    suspend fun getPaperInfoIds(userId: String, reportId: String): List<String>

    @Transaction
    @Query(
        """
            SELECT 
                paper_info.*, trend_info.level, trend_info.direction, trend_info.student_number 
            FROM paper_info
            LEFT JOIN trend_info
                ON trend_info.user_id = :userId 
                AND trend_info.paper_id = paper_info.id
                AND trend_info.code = 'clazz'
            WHERE paper_info.user_id = :userId AND paper_info.report_id = :reportId
            ORDER BY subject_code
        """
    )
    suspend fun getPaperInfoList(userId: String, reportId: String): List<PopulatedPaperInfo>
}