package com.zhixue.lite.feature.paper

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.request.ImageRequest
import com.zhixue.lite.core.designsystem.component.AsyncImage
import com.zhixue.lite.core.designsystem.component.Divider
import com.zhixue.lite.core.designsystem.component.Icon
import com.zhixue.lite.core.designsystem.component.IconButton
import com.zhixue.lite.core.designsystem.component.Text
import com.zhixue.lite.core.designsystem.modifier.themePlaceholder
import com.zhixue.lite.core.designsystem.theme.Theme
import com.zhixue.lite.core.model.PaperDetail
import com.zhixue.lite.core.model.SheetPage
import com.zhixue.lite.core.ui.ScorePanel
import com.zhixue.lite.feature.paper.util.SheetImageTransformation

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
        Divider()
        SheetPanel(enabledPlaceholder = true)
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
        Divider()
        SheetPanel(
            sheetPages = paperDetail.sheetPages
        )
    }
}

@Composable
fun SheetPanel(
    sheetPages: List<SheetPage> = emptyList(),
    enabledPlaceholder: Boolean = false
) {
    Column(modifier = Modifier.padding(vertical = 16.dp, horizontal = 24.dp)) {
        Text(
            text = stringResource(R.string.paper_sheet_label),
            color = Theme.colorScheme.onBackgroundVariant,
            style = Theme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier
                .padding(vertical = 8.dp)
                .width(if (enabledPlaceholder) 48.dp else Dp.Unspecified)
                .themePlaceholder(enabledPlaceholder)
        )
        Column(modifier = Modifier.clip(Theme.shapes.small)) {
            if (sheetPages.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(192.dp)
                        .themePlaceholder(enabledPlaceholder)
                )
            } else {
                sheetPages.forEach { sheetPage ->
                    SheetPage(sheetPage = sheetPage)
                }
            }
        }
    }
}

@Composable
internal fun SheetPage(
    sheetPage: SheetPage,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(sheetPage.image)
            .transformations(
                SheetImageTransformation(
                    width = sheetPage.width,
                    height = sheetPage.height,
                    sections = sheetPage.sections
                )
            )
            .diskCacheKey(sheetPage.image.substringBefore("?"))
            .memoryCacheKey(sheetPage.image.substringBefore("?"))
            .build(),
        modifier = modifier
    )
}