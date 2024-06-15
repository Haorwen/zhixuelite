package com.zhixue.lite.core.model

data class SheetInfo(
    val userScore: Double,
    val standardScore: Double,
    val pages: List<SheetPage>
)