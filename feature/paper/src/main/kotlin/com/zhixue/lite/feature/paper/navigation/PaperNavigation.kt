package com.zhixue.lite.feature.paper.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zhixue.lite.feature.paper.PaperRoute
import kotlinx.serialization.Serializable

@Serializable
data class PaperRoute(val paperId: String)

fun NavController.navigateToPaper(paperId: String, navOptions: NavOptions? = null) {
    navigate(PaperRoute(paperId), navOptions)
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