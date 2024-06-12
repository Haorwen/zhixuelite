package com.zhixue.lite.feature.paper.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zhixue.lite.feature.paper.PaperRoute
import kotlinx.serialization.Serializable

@Serializable
data class PaperRoute(val reportId: String, val paperId: String)

fun NavController.navigateToPaper(
    reportId: String,
    paperId: String,
    navOptions: NavOptions? = null
) {
    navigate(PaperRoute(reportId, paperId), navOptions)
}

fun NavGraphBuilder.paperScreen(
    onBackClick: () -> Unit
) {
    composable<PaperRoute> {
        PaperRoute(
            onBackClick = onBackClick
        )
    }
}