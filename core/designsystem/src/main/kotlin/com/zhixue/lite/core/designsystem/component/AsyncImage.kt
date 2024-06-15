package com.zhixue.lite.core.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AsyncImage(
    model: Any,
    modifier: Modifier = Modifier
) {
    coil.compose.AsyncImage(
        model = model,
        contentDescription = null,
        modifier = modifier
    )
}