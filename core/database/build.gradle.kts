plugins {
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.zhixuelite.android.library)
    alias(libs.plugins.zhixuelite.android.hilt)
    alias(libs.plugins.zhixuelite.android.room)
}

android {
    namespace = "com.zhixue.lite.core.database"
}

dependencies {
    api(projects.core.model)
    implementation(libs.androidx.room.paging)
    implementation(libs.kotlinx.serialization.json)
}