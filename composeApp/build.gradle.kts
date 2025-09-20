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
            implementation(compose.preview)
            implementation("androidx.activity:activity-compose:1.11.0")

            implementation("io.ktor:ktor-client-android:3.3.0")
            implementation("io.ktor:ktor-client-okhttp:3.3.0")
        }
        commonMain.dependencies {


            //Lifecycle
            implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")
            implementation("org.jetbrains.androidx.lifecycle:lifecycle-runtime-compose:2.9.4")

            //Navigation
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.9.0")

            //Serialization
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")

            //Koin
            val koinVersion = "4.1.1"
            implementation("io.insert-koin:koin-compose:$koinVersion")
            implementation("io.insert-koin:koin-compose-viewmodel:$koinVersion")
            implementation("io.insert-koin:koin-compose-viewmodel-navigation:$koinVersion")

            //Kotor
            val ktorVersion = "3.3.0"
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-client-logging:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-client-websockets:$ktorVersion")


            //Coroutines
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")


            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
        }

        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:3.3.0")
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

