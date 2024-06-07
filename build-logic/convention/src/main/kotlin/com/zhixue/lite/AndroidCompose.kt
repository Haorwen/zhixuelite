package com.zhixue.lite

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
        }

        dependencies {
            add("implementation", libs.androidx.compose.foundation)
            add("implementation", libs.androidx.compose.ui.tooling.preview)
        }
    }
}