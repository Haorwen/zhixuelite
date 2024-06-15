package com.zhixue.lite.feature.paper

import androidx.annotation.MainThread
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.zhixue.lite.core.data.repository.PaperRepository
import com.zhixue.lite.core.domain.GetPaperDetailUseCase
import com.zhixue.lite.core.model.PaperDetail
import com.zhixue.lite.feature.paper.navigation.PaperRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaperViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val paperRepository: PaperRepository,
    private val getPaperDetailUseCase: GetPaperDetailUseCase
) : ViewModel() {

    var uiState: PaperUiState by mutableStateOf(PaperUiState.Loading)
        private set

    private val savedState: PaperRoute = savedStateHandle.toRoute()

    private val reportId: String = savedState.reportId
    private val paperId: String = savedState.paperId

    private var isInitialized: Boolean = false

    @MainThread
    fun initialize() {
        if (isInitialized) return
        isInitialized = true

        viewModelScope.launch {
            paperRepository.syncSheetInfo(reportId, paperId)
            runCatching {
                getPaperDetailUseCase(paperId)
            }.onSuccess { paperDetail ->
                uiState = PaperUiState.Success(paperDetail)
            }.onFailure { throwable ->
                throwable.printStackTrace()
                uiState = PaperUiState.Failure
            }
        }
    }
}

sealed class PaperUiState {
    data object Loading : PaperUiState()
    data object Failure : PaperUiState()
    data class Success(val paperDetail: PaperDetail) : PaperUiState()
}