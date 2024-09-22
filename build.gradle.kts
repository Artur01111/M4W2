// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("androidx.navigation.safeargs.kotlin") version "2.8.0" apply false
    id("com.google.devtools.ksp") version "2.0.20-1.0.24" apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}