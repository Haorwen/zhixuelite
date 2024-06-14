package com.zhixue.lite.core.model

import kotlinx.serialization.Serializable

@Serializable
data class SheetSection(
    val x: Int,
    val y: Int,
    val width: Int,
    val score: Double,
    val standardScore: Double
)