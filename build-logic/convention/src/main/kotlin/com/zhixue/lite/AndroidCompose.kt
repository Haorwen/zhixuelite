package com.zhixue.lite

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {
            add("implementation", libs.androidx.compose.foundation)
            add("implementation", libs.androidx.compose.ui.tooling.preview)
        }

        extensions.configure<ComposeCompilerGradlePluginExtension> {
            enableStrongSkippingMode.set(true)
        }
    }
}