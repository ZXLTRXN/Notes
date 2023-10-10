plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
}

android {
    namespace = "com.example.api.notes"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(libs.bundles.coroutines)
    implementation(libs.dagger)

    kapt(libs.dagger.compiler)

    implementation(project(":core:common"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.android.test)
}