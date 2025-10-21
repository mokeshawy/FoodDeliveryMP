plugins {

    val apgVersion = "8.10.1"
    val kotlinVersion = "2.2.10"
    id("com.android.application") version apgVersion apply false
    id("com.android.library") version apgVersion apply false
    id("org.jetbrains.kotlin.plugin.compose") version kotlinVersion apply false
    id("org.jetbrains.kotlin.multiplatform") version kotlinVersion apply false
    id("org.jetbrains.compose") version "1.9.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion apply false
    id("com.android.kotlin.multiplatform.library") version "8.10.1" apply false
    id("com.google.devtools.ksp") version "2.2.0-2.0.2" apply false
}