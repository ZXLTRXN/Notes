// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.parcelize) apply false
}

allprojects {
    if(name.equals("feature") || name.equals("api")) {
        subprojects {
            apply {
                plugin("kotlin-kapt")
            }
        }
    }
}