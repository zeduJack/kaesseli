plugins {
    //trick: for the same plugin versions in all sub-modules
    val kotlinVersion = "1.7.20"
    val agpVersion = "7.2.0" // this is the highest version that is supported both in IntelliJ & Android Studio (23.11.2022)

    id("com.android.application").version(agpVersion).apply(false)
    id("com.android.library").version(agpVersion).apply(false)
    kotlin("android").version(kotlinVersion).apply(false)
    kotlin("multiplatform").version(kotlinVersion).apply(false)
    kotlin("plugin.serialization").version(kotlinVersion).apply(false)
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

