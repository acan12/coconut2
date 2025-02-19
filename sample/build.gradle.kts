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
            isDebuggable = false
            isMinifyEnabled = false
//            proguardFiles(
//                getDefaultProguardFile("proguard-android-optimize.txt"),
//                "proguard-rules.pro"
//            )
        }
        debug {
            isDebuggable = true
            applicationIdSuffix = ".debug"
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
        buildConfig = true
    }

    flavorDimensions += "version"
    productFlavors {
        create("staging") {
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"
            buildConfigField("String", "SERVER_URL", "\"https:/user-bogasari.ptape.com\"")
            buildConfigField("String", "NEWSORG_APIKEY", "\"6d362365d5e245faa1fe3253c83c45ac\"")
            buildConfigField("String", "DB_NAME", "\"coconut-sample-db\"")
        }

        create("production") {
            buildConfigField("String", "SERVER_URL", "\"https:/user-bogasari.com\"")
            buildConfigField("String", "NEWSORG_APIKEY", "\"6d362365d5e245faa1fe3253c83c45ac\"")
            buildConfigField("String", "DB_NAME", "\"coconut-sample-db\"")
        }
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
    implementation(libs.androidx.activity)
    // room database
    implementation(libs.androidx.room.ktx)
    // video
    implementation(libs.camera.video)
    implementation(libs.camera.lifecycle)
    implementation(libs.camera.view)
    implementation(libs.camera.camera2)
    implementation(libs.camera.core)

    // unit test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}