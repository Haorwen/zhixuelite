package com.zhixue.lite.core.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.zhixue.lite.core.data.model.asEntity
import com.zhixue.lite.core.database.dao.RemotePageDao
import com.zhixue.lite.core.database.dao.ReportInfoDao
import com.zhixue.lite.core.database.dao.SubjectInfoDao
import com.zhixue.lite.core.database.model.ReportInfoEntity
import com.zhixue.lite.core.database.model.SubjectInfoEntity
import com.zhixue.lite.core.database.model.asExternalModel
import com.zhixue.lite.core.model.ReportInfo
import com.zhixue.lite.core.model.SubjectInfo
import com.zhixue.lite.core.network.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ReportRepositoryImpl @Inject constructor(
    private val userRepository: UserRepository,
    private val remotePageDao: RemotePageDao,
    private val reportInfoDao: ReportInfoDao,
    private val subjectInfoDao: SubjectInfoDao,
    private val networkDataSource: NetworkDataSource
) : ReportRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getReportInfoList(reportType: String): Flow<PagingData<ReportInfo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            remoteMediator = ReportInfoRemoteMediator(
                reportType = reportType,
                userRepository = userRepository,
                remotePageDao = remotePageDao,
                reportInfoDao = reportInfoDao,
                networkDataSource = networkDataSource
            ),
            pagingSourceFactory = {
                reportInfoDao.getReportInfoPagingSource(reportType)
            }
        ).flow.map { pagingData ->
            pagingData.map(ReportInfoEntity::asExternalModel)
        }
    }

    override suspend fun getSubjectInfoList(reportId: String): List<SubjectInfo> {
        try {
            val subjectInfoEntities = networkDataSource.getReportMain(
                reportId = reportId,
                token = userRepository.getUserToken()
            ).subjectList.map { it.asEntity(reportId) }

            subjectInfoDao.insertSubjectInfoEntities(subjectInfoEntities)
        } catch (_: Exception) {
        }

        return subjectInfoDao.getSubjectInfoEntities(reportId)
            .map(SubjectInfoEntity::asExternalModel)
    }
}