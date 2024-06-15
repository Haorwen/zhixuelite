package com.zhixue.lite.core.domain

import com.zhixue.lite.core.data.repository.PaperRepository
import com.zhixue.lite.core.model.PaperDetail
import com.zhixue.lite.core.model.ScoreInfo
import javax.inject.Inject

class GetPaperDetailUseCase @Inject constructor(
    private val paperRepository: PaperRepository
) {
    suspend operator fun invoke(paperId: String): PaperDetail {
        return paperRepository.getSheetInfo(paperId).let {
            PaperDetail(
                scoreInfo = ScoreInfo(
                    userScore = it.userScore,
                    standardScore = it.standardScore
                ),
                sheetPages = it.pages
            )
        }
    }
}