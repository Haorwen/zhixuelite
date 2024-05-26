package com.zhixue.lite.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.zhixue.lite.feature.home.HomeRoute
import kotlinx.serialization.Serializable

@Serializable
object Home

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    navigate(Home, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onReportInfoClick: (String) -> Unit
) {
    composable<Home> {
        HomeRoute(onReportInfoClick = onReportInfoClick)
    }
}