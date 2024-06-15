package com.zhixue.lite.feature.paper

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zhixue.lite.core.designsystem.component.Icon
import com.zhixue.lite.core.designsystem.component.IconButton
import com.zhixue.lite.core.designsystem.component.Text
import com.zhixue.lite.core.designsystem.theme.Theme
import com.zhixue.lite.core.model.PaperDetail
import com.zhixue.lite.core.ui.ScorePanel

@Composable
internal fun PaperRoute(
    onBackClick: () -> Unit,
    viewModel: PaperViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.initialize()
    }

    PaperScreen(
        uiState = viewModel.uiState,
        onBackClick = onBackClick
    )
}

@Composable
internal fun PaperScreen(
    uiState: PaperUiState,
    onBackClick: () -> Unit
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        PaperHeader(onBackClick = onBackClick)
        PaperContent(
            uiState = uiState
        )
    }
}

@Composable
internal fun PaperHeader(onBackClick: () -> Unit) {
    Column {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                painter = painterResource(com.zhixue.lite.core.common.R.drawable.ic_back),
                tint = Theme.colorScheme.onBackground
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.paper_title),
            color = Theme.colorScheme.onBackground,
            style = Theme.typography.headline,
            modifier = Modifier.padding(horizontal = 32.dp)
        )
    }
}

@Composable
internal fun PaperContent(uiState: PaperUiState) {
    Crossfade(
        label = "UiState",
        targetState = uiState
    ) { targetUiState ->
        when (targetUiState) {
            PaperUiState.Loading -> PaperPlaceholderBody()
            PaperUiState.Failure -> PaperErrorBody()
            is PaperUiState.Success -> PaperDetailBody(
                paperDetail = targetUiState.paperDetail
            )
        }
    }
}

@Composable
internal fun PaperPlaceholderBody() {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .border(1.dp, Theme.colorScheme.outline, Theme.shapes.medium)
    ) {
        ScorePanel(enabledPlaceholder = true)
    }
}

@Composable
internal fun PaperErrorBody() {
    Box(modifier = Modifier.padding(horizontal = 32.dp)) {
        Text(
            text = stringResource(R.string.paper_error_message),
            color = Theme.colorScheme.error,
            style = Theme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
internal fun PaperDetailBody(
    paperDetail: PaperDetail
) {
    Column(
        modifier = Modifier
            .padding(24.dp)
            .border(1.dp, Theme.colorScheme.outline, Theme.shapes.medium)
    ) {
        ScorePanel(
            label = stringResource(R.string.paper_score_label),
            scoreInfo = paperDetail.scoreInfo
        )
    }
}