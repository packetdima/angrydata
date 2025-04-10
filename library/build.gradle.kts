plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

val targetName = "native"

kotlin {
    jvm()
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val isArm64 = System.getProperty("os.arch") == "aarch64"
    val nativeTarget = when {
        hostOs == "Mac OS X" && !isArm64 -> macosX64(targetName)
        hostOs == "Linux" && !isArm64 -> linuxX64(targetName)
        hostOs == "Mac OS X" && isArm64 -> macosArm64(targetName)
        hostOs == "Linux" && isArm64 -> linuxArm64(targetName)
        isMingwX64 -> mingwX64(targetName)
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    nativeTarget.apply {
        binaries {
            sharedLib {
                baseName = rootProject.name
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":kotlin-lib"))
            }
        }
        val commonTest by getting {
            dependencies {
            }
        }
    }
}