plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
    id ("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.douglas2990.pokedexapimvvmcleanarchitecturehilt"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.douglas2990.pokedexapimvvmcleanarchitecturehilt"
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
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation (libs.retrofit)
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation (libs.converter.gson)
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation (libs.logging.interceptor)
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation (libs.okhttp)
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")
    implementation (libs.fresco)
    implementation ("com.facebook.fresco:fresco:2.5.0")
    implementation (libs.shimmer)
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
    implementation (libs.picasso)
    implementation ("com.squareup.picasso:picasso:2.71828")

    implementation (libs.glide)
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor (libs.compiler)
    annotationProcessor ("com.github.bumptech.glide:compiler:4.12.0")



    // ViewModel
    implementation ( libs.androidx.lifecycle.viewmodel.ktx)
    // ViewModel utilities for Compose
    implementation (libs.androidx.lifecycle.viewmodel.compose)
    // LiveData
    implementation (libs.lifecycle.livedata.ktx)
    // Lifecycles only (without ViewModel or LiveData)
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    // Lifecycle utilities for Compose
    implementation ("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")

    // Saved state module for ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.7.0")

    // Annotation processor
    kapt ("androidx.lifecycle:lifecycle-compiler:2.7.0")
    //ksp("androidx.lifecycle:lifecycle-compiler:2.7.0")
    // alternately - if using Java8, use the following instead of lifecycle-compiler
    implementation (libs.androidx.lifecycle.common.java8)

    // optional - helpers for implementing LifecycleOwner in a Service
    implementation (libs.androidx.lifecycle.service)

    // optional - ProcessLifecycleOwner provides a lifecycle for the whole application process
    implementation (libs.androidx.lifecycle.process)

    // optional - ReactiveStreams support for LiveData
    implementation (libs.androidx.lifecycle.reactivestreams.ktx)

    // optional - Test helpers for LiveData
    testImplementation (libs.androidx.core.testing)

    // optional - Test helpers for Lifecycle runtime
    testImplementation (libs.androidx.lifecycle.runtime.testing)


    //implementation ("com.google.dagger:hilt-android:2.28-alpha")
    //kapt ("com.google.dagger:hilt-android-compiler:2.28-alpha")
    //ksp ("com.google.dagger:hilt-android-compiler:2.28-alpha")

    //hilt
    //implementation ("com.google.dagger:hilt-android:2.42")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
    //kapt ("com.google.dagger:hilt-compiler:2.42")
    //ksp ("com.google.dagger:hilt-compiler:2.42")
    //kapt ("androidx.hilt:hilt-compiler:1.0.0")
    //kapt ("androidx.hilt:hilt-compiler:1.2.0")
    //ksp ("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")

    implementation("io.coil-kt:coil:2.6.0")
    implementation("io.coil-kt:coil-compose:2.6.0")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

hilt {
    enableAggregatingTask = true
}