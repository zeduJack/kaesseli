// if gradle has some problems do in terminal:
// ./gradlew --refresh-dependencies

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

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        val kotlinVersion = project.findProperty("kaesseli.kotlin.version")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.android.tools.build:gradle:7.2.2")
    }
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