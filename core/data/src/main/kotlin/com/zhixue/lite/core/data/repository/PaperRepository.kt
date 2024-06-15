package com.zhixue.lite.core.data.repository

import com.zhixue.lite.core.model.PaperInfo
import com.zhixue.lite.core.model.SheetInfo

interface PaperRepository {

    suspend fun getPaperInfoIds(reportId: String): List<String>

    suspend fun getPaperInfoList(reportId: String): List<PaperInfo>

    suspend fun getSheetInfo(paperId: String): SheetInfo

    suspend fun syncPaperInfoList(reportId: String)

    suspend fun syncTrendInfoList(reportId: String, paperId: String)

    suspend fun syncSheetInfo(reportId: String, paperId: String)
}