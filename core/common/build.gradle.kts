plugins {
    alias(libs.plugins.zhixuelite.android.library)
    alias(libs.plugins.zhixuelite.android.hilt)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.zhixue.lite.core.common"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}