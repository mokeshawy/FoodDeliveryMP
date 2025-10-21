import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("com.android.application")
    id("org.jetbrains.compose")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }


    sourceSets {

        androidMain.dependencies {
            implementation(project(":core-cmp"))
        }
        commonMain.dependencies {
            implementation(project(":core-cmp"))
            implementation(compose.components.resources)
        }

        iosMain.dependencies {
            implementation(project(":core-cmp"))
        }
        commonTest.dependencies {
            implementation("org.jetbrains.kotlin:kotlin-test:2.2.10")
        }
    }
}

android {
    namespace = "org.saham.fooddelivery"
    compileSdk = 36

    defaultConfig {
        applicationId = "org.saham.fooddelivery"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

