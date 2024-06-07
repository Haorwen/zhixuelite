package com.zhixue.lite.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkCasInfo(
    @SerialName("role")
    val role: String,
    @SerialName("token")
    val token: String,
    @SerialName("childrens")
    val children: List<ChildInfo>? = null,
    @SerialName("userInfo")
    val userInfo: UserInfo,
    @SerialName("clazzInfo")
    val classInfo: ClassInfo? = null,
) {
    @Serializable
    data class ChildInfo(
        @SerialName("id")
        val id: String,
        @SerialName("userInfo")
        val userInfo: UserInfo,
        @SerialName("clazzInfo")
        val classInfo: ClassInfo
    )

    @Serializable
    data class UserInfo(
        @SerialName("avatar")
        val avatar: String? = null,
        @SerialName("name")
        val name: String,
        @SerialName("school")
        val schoolInfo: SchoolInfo? = null
    )

    @Serializable
    data class SchoolInfo(
        @SerialName("schoolName")
        val name: String
    )

    @Serializable
    data class ClassInfo(
        @SerialName("name")
        val name: String
    )
}