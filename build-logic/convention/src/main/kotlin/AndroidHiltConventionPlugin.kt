import com.zhixue.lite.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = libs.plugins.hilt.android.get().pluginId)
            apply(plugin = libs.plugins.ksp.get().pluginId)

            dependencies {
                add("implementation", libs.hilt.android)
                add("ksp", libs.hilt.android.compiler)
            }
        }
    }
}