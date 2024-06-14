package com.zhixue.lite.core.model

import kotlinx.serialization.Serializable

@Serializable
data class SheetPage(
    val width: Int?,
    val height: Int?,
    val image: String,
    val sections: List<SheetSection>
)