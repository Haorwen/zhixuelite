package com.zhixue.lite.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkReportMain(
    @SerialName("paperList")
    val paperInfoList: List<NetworkPaperInfo>
)

@Serializable
data class NetworkPaperInfo(
    @SerialName("paperId")
    val paperId: String,
    @SerialName("subjectName")
    val subjectName: String,
    @SerialName("userScore")
    val userScore: Double,
    @SerialName("standardScore")
    val standardScore: Double
)