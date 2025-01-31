plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "app.coconut2.sample"
    compileSdk = 35

    defaultConfig {
        applicationId = "app.coconut2.sample"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":coconut2-mvvm"))

    // androidx
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    // dagger-hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)
    // caroutines
    implementation(libs.caroutines)
    implementation(libs.caroutines.core)
    // rx-android3
    implementation(libs.rx.android3)
    implementation(platform(libs.okhttp.bom))
    // okhttp
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    // retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.jackson)
    // viewmodel livedata
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.livedata)

    // unit test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}