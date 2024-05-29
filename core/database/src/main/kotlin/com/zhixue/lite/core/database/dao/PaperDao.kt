package com.zhixue.lite.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.zhixue.lite.core.database.model.PaperInfoEntity
import com.zhixue.lite.core.database.model.PopulatedPaperInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface PaperDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPaperInfoList(entities: List<PaperInfoEntity>)

    @Transaction
    @Query("SELECT * FROM paper_info WHERE report_id = :reportId")
    suspend fun getPaperInfoStream(reportId: String): Flow<List<PopulatedPaperInfo>>

    @Query("SELECT paper_id FROM paper_info WHERE report_id = :reportId ")
    suspend fun getPaperInfoIds(reportId: String): List<String>
}