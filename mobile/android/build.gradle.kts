plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
}

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "ch.levelup.kaesseli.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.0")
    implementation("com.google.firebase:firebase-messaging-ktx:23.0.6")
}