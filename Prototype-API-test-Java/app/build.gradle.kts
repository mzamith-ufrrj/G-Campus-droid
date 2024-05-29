plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "tn743.ufrrj.gcampus.j_g_campus_test"
    compileSdk = 34

    defaultConfig {
        applicationId = "tn743.ufrrj.gcampus.j_g_campus_test"
        minSdk = 26
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

}

dependencies {
    /* implementation("com.squareup.okhttp3:okhttp:4.9.0") */
    /* implementation("com.google.android.gms:play-services-vision:20.1.3") */
    //implementation("com.google.zxing:javase:3.5.3_0")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}