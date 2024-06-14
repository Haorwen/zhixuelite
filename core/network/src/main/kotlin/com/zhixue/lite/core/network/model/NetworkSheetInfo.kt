package com.zhixue.lite.core.network.model

import com.zhixue.lite.core.common.json.JsonString
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSheetInfo(
    @SerialName("sheetDatas")
    val data: JsonString<Data>,
    @SerialName("sheetImages")
    val images: JsonString<List<String>>
) {
    @Serializable
    data class Data(
        @SerialName("answerSheetLocationDTO")
        val location: Location? = null,
        @SerialName("userAnswerRecordDTO")
        val answerRecord: AnswerRecord
    ) {
        @Serializable
        data class Location(
            @SerialName("comeFrom")
            val comeFrom: Int,
            @SerialName("paperType")
            val paperType: String,
            @SerialName("pageSheets")
            val pages: List<NetworkSheetPage>
        )

        @Serializable
        data class AnswerRecord(
            @SerialName("answerRecordDetails")
            val list: List<NetworkAnswerRecordInfo>
        )
    }
}

@Serializable
data class NetworkSheetPage(
    @SerialName("widthAfterCorrect")
    val width: Double? = null,
    @SerialName("heightAfterCorrect")
    val height: Double? = null,
    @SerialName("sections")
    val sections: List<NetworkSheetSection>
)

@Serializable
data class NetworkSheetSection(
    @SerialName("contents")
    val contents: Contents
) {
    @Serializable
    data class Contents(
        @SerialName("branch")
        val branches: List<Branch>,
        @SerialName("position")
        val position: Position
    ) {
        @Serializable
        data class Branch(
            @SerialName("ixList")
            val indexes: List<Int>
        )

        @Serializable
        data class Position(
            @SerialName("left")
            val x: Int,
            @SerialName("top")
            val y: Int,
            @SerialName("width")
            val width: Int
        )
    }
}

@Serializable
data class NetworkAnswerRecordInfo(
    @SerialName("score")
    val score: Double = 0.0,
    @SerialName("standardScore")
    val standardScore: Double
)