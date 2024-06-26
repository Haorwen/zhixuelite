package com.zhixue.lite.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.zhixue.lite.core.domain.FormatReportInfoListUseCase
import com.zhixue.lite.core.model.FormatReportInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    formatReportInfoUseCase: FormatReportInfoListUseCase
) : ViewModel() {

    val examReportInfoList: Flow<PagingData<FormatReportInfo>> =
        formatReportInfoUseCase(reportType = "exam").cachedIn(viewModelScope)

    val homeworkReportInfoList: Flow<PagingData<FormatReportInfo>> =
        formatReportInfoUseCase(reportType = "homework").cachedIn(viewModelScope)
}