package com.zhixue.lite.core.model

data class ScoreInfo internal constructor(
    val userScore: String,
    val standardScore: String,
    val scoreRate: Float
) {
    constructor(userScore: Double, standardScore: Double) : this(
        userScore = userScore.toBigDecimal().stripTrailingZeros().toPlainString(),
        standardScore = standardScore.toBigDecimal().stripTrailingZeros().toPlainString(),
        scoreRate = (userScore / standardScore).toFloat()
    )
}