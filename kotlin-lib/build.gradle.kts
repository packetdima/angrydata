import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.ByteArrayOutputStream

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.kover)
    alias(libs.plugins.shadow)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.kotlin.serialization)
}

group = "info.downdetector.bigdatascanner"
version = "1.0.9"
description = "Data Scanner Library"


val targetName = "native"

kotlin {
    jvm {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }

        val mainCompilation = compilations.getByName("main")
        tasks.register<ShadowJar>("jvmShadowJar") { // create fat jar task
            val jvmRuntimeConfiguration = mainCompilation
                .runtimeDependencyConfigurationName
                .let { project.configurations.getByName(it) }

            from(mainCompilation.output.allOutputs) // allOutputs == classes + resources
            configurations = listOf(jvmRuntimeConfiguration)
            archiveClassifier.set("fatjar")
        }
    }

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
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.serialization)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

fun String.runCommand(currentWorkingDir: File = file("./")): String {
    val byteOut = ByteArrayOutputStream()
    project.exec {
        workingDir = currentWorkingDir
        commandLine = this@runCommand.split("\\s".toRegex())
        standardOutput = byteOut
    }
    return String(byteOut.toByteArray()).trim()
}

val gitBranch = System.getProperty("GIT_BRANCH") ?: "git rev-parse --abbrev-ref HEAD".runCommand()

tasks.create("printVersion") {
    doLast {
        print(version)
    }
}

koverReport {
    filters {
        includes {
            classes("*")
        }
    }
}

mavenPublishing {
    coordinates(
        groupId = "info.downdetector.bigdatascanner",
        artifactId = "datascanner",
        version = project.version.toString()
    )

    pom {
        name.set("Big Data Scanner library for Java and Kotlin")
        description.set("Big Data Scanner library for Java and Kotlin")
        url.set("https://github.com/packetdima/angrydata")

        licenses {
            license {
                name.set("Apache-2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0")
            }
        }

        developers {
            developer {
                id.set("stellalupus")
                name.set("StellaLupus")
                email.set("soulofpain.k@gmail.com")
            }
        }

        scm {
            url.set("https://github.com/packetdima/angrydata")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()
}