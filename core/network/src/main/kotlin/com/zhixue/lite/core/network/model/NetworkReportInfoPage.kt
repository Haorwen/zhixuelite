package com.zhixue.lite.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkReportInfoPage(
    @SerialName("hasNextPage")
    val hasNextPage: Boolean,
    @SerialName("examInfoList")
    val list: List<NetworkReportInfo>
)

@Serializable
data class NetworkReportInfo(
    @SerialName("examId")
    val id: String,
    @SerialName("examName")
    val name: String,
    @SerialName("examCreateDateTime")
    val createDate: Long,
    @SerialName("isSinglePublish")
    val isPublished: Boolean = true
)