package com.zhixue.lite.feature.report.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.zhixue.lite.feature.report.ReportRoute
import kotlinx.serialization.Serializable

@Serializable
data class ReportRoute(val reportId: String)

fun NavController.navigateToReport(reportId: String, navOptions: NavOptions? = null) {
    navigate(ReportRoute(reportId), navOptions)
}

fun NavGraphBuilder.reportScreen(
    onBackClick: () -> Unit,
    onPaperInfoClick: (String, String) -> Unit
) {
    composable<ReportRoute> {
        val reportId = it.toRoute<ReportRoute>().reportId
        ReportRoute(
            onBackClick = onBackClick,
            onPaperInfoClick = { paperId -> onPaperInfoClick(reportId, paperId) }
        )
    }
}