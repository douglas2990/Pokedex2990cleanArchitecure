//import com.chaquo.python.pythonVersionInfo

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.chaquopy)
    //id("com.chaquo.python")
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)

}

android {
    namespace = "com.douglas2990.pokedexapimvvmcleanarchitecturehilt"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.douglas2990.pokedexapimvvmcleanarchitecturehilt"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


        ndk {
            abiFilters += listOf("armeabi-v7a", "arm64-v8a", "x86", "x86_64")
        }


        // O BLOCO PYTHON DEVE FICAR AQUI DENTRO


        // Sintaxe alternativa caso a simples continue falhando


    }

    chaquopy {
        defaultConfig {
            version = "3.11"
            pip {
                install("requests")
            }
        }
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

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    ksp(libs.androidx.room.compiler)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Network & Images
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.glide)
    implementation(libs.coil)

    // Shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")
    implementation(libs.fresco)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}
