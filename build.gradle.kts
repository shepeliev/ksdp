import org.gradle.kotlin.dsl.support.uppercaseFirstChar
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

group = "com.shepeliev"
version = "1.0-SNAPSHOT"

kotlin {
    explicitApi()

    androidTarget {
        publishAllLibraryVariants()
        compilerOptions { jvmTarget = JvmTarget.JVM_11 }
    }

    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()
    js {
        useCommonJs()
        browser {
            testTask {
                useKarma { useChromeHeadless() }
            }
        }
        nodejs()
    }
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            testTask {
                useKarma { useChromeHeadless() }
            }
        }
        nodejs()
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlin.datetime)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.shepeliev.ksdp"

    compileSdk = libs.versions.compileSdk.get().toInt()
    sourceSets["main"].res.srcDir("src/androidMain/res")

    sourceSets["testDebug"].resources.srcDir("src/commonTest/resources")
    sourceSets["testRelease"].resources.srcDir("src/commonTest/resources")

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

afterEvaluate {
    copyResources("iosX64")
    copyResources("iosSimulatorArm64")
    copyResources("macosX64")
    copyResources("macosArm64")
}

fun copyResources(platform: String) {
    tasks.register<Copy>("copyResources${platform.uppercaseFirstChar()}DebugTest") {
        from("$projectDir/src/commonTest/resources")
        into("$buildDir/bin/$platform/debugTest/")
    }
    tasks.getByName("${platform}Test") {
        dependsOn("copyResources${platform.uppercaseFirstChar()}DebugTest")
    }
}
