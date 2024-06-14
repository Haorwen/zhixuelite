import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.zhixue.lite.configureAndroidCompose
import com.zhixue.lite.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.plugins.compose.compiler.get().pluginId)

            when {
                pluginManager.hasPlugin(libs.plugins.android.application.get().pluginId) -> {
                    configure<ApplicationExtension> { configureAndroidCompose(this) }
                }

                pluginManager.hasPlugin(libs.plugins.android.library.get().pluginId) -> {
                    configure<LibraryExtension> { configureAndroidCompose(this) }
                }
            }
        }
    }
}