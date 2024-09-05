
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.androidx.navigation.safeargs)
    alias(libs.plugins.ksp)
    alias(libs.plugins.parcelize)
    id("com.google.gms.google-services")

}

android {
    namespace = "com.example.newsapp"
    compileSdk = 34

    buildFeatures {
        viewBinding= true
    }

    defaultConfig {
        applicationId = "com.example.newsapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    //added for lifecycle
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    // added for room
    implementation(libs.room)
    implementation(libs.room.runtime)
    implementation(libs.androidx.ui.desktop)
    implementation(libs.androidx.fragment)
    implementation(libs.firebase.firestore)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)
    //added for retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.gson)
    implementation(libs.glide)
    //added for navigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    //animation
    implementation(libs.lottie.animaton)
    //androidYoutubePlayer
    implementation(libs.androidYoutubePlayer)

    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}