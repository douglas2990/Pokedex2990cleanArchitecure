// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    // Usando o nome exato definido no TOML: libs.plugins.ksp
    alias(libs.plugins.ksp) apply false

    // Usando o nome exato definido no TOML: libs.plugins.hilt
    alias(libs.plugins.hilt) apply false
}
