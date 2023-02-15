plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")
}
// https://antran.app/2022/kotlin_multiplatform_mobile_jetpack_compose/
// Added for Jetpack Compose
// Since the project is using Kotlin 1.6.21, we have to use this rc02 version of Jetpack Compose as of 28.06.2022
// https://developer.android.com/jetpack/androidx/releases/compose-kotlin
// https://kotlinlang.org/docs/releases.html#release-details
val composeVersion = "1.3.1"
val composeNavVersion = "2.5.2"

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "ch.levelup.kaesseli.android"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    // Added for Jetpack Compose
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":mobile:common"))
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Added for Jetpack Compose
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    implementation("androidx.test:monitor:1.6.1")
    // Jetpack Compose navigation
    implementation("androidx.navigation:navigation-compose:$composeNavVersion")
    implementation("com.google.firebase:firebase-messaging-ktx:23.1.1")

    // Added for Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-tooling:$composeVersion")
    debugImplementation("androidx.compose.ui:ui-test-manifest:$composeVersion")
}
