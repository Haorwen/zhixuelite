plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.compose.compiler.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.room.gradle.plugin)
    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("android-application") {
            id = "zhixuelite.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("android-compose") {
            id = "zhixuelite.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("android-feature") {
            id = "zhixuelite.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("android-hilt") {
            id = "zhixuelite.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("android-library") {
            id = "zhixuelite.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("android-room") {
            id = "zhixuelite.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvm-library") {
            id = "zhixuelite.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}