plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

group = "com.shepeliev"
version = "1.0-SNAPSHOT"

kotlin {
    explicitApi()

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kermit)
            implementation(libs.betterParse)
            api(libs.kotlin.datetime)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}
