package com.zhixue.lite.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.zhixue.lite.feature.login.navigation.loginScreen
import com.zhixue.lite.feature.main.navigation.mainScreen
import com.zhixue.lite.feature.paper.navigation.paperScreen
import com.zhixue.lite.feature.report.navigation.reportScreen
import com.zhixue.lite.ui.ZhixueLiteAppState

@Composable
fun ZhixueLiteNavHost(
    appState: ZhixueLiteAppState,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = appState.navController,
        startDestination = appState.currentStartDestination,
        modifier = modifier,
        enterTransition = {
            slideInHorizontally(tween(375)) { it / 2 } + fadeIn(tween(0))
        },
        popEnterTransition = {
            slideInHorizontally(tween(375)) { -it / 2 } + fadeIn(tween(0))
        },
        exitTransition = {
            fadeOut(tween(0))
        },
    ) {
        loginScreen(
            onLoginSuccess = appState::navigateToMain,
            onRegisterClick = {},
            onForgetPasswordClick = {}
        )
        mainScreen(
            onLogoutClick = appState::navigateToLogin,
            onReportInfoClick = appState::navigateToReport
        )
        reportScreen(
            onBackClick = appState::popBackStack,
            onPaperInfoClick = appState::navigateToPaper
        )
        paperScreen(
            onBackClick = appState::popBackStack
        )
    }
}