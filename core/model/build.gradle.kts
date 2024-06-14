plugins {
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.zhixuelite.jvm.library)
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}