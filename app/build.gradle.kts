plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id ("kotlin-parcelize")
    id ("kotlin-kapt")
}

android {
    namespace = "com.id.masel.healtime"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.id.masel.healtime"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL",  "")

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
    buildFeatures{
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

implementation(libs.androidx.core.ktx)
implementation(libs.androidx.appcompat)
implementation(libs.material)
implementation(libs.androidx.activity)
implementation(libs.androidx.constraintlayout)
implementation(libs.androidx.navigation.fragment)
implementation(libs.androidx.legacy.support.v4)
implementation(libs.androidx.lifecycle.livedata.ktx)
implementation(libs.androidx.lifecycle.viewmodel.ktx)
implementation(libs.androidx.fragment.ktx)

//Navigation
implementation(libs.androidx.navigation.fragment.ktx)
implementation(libs.androidx.navigation.ui.ktx)

//Room
implementation(libs.androidx.room.runtime)
implementation(libs.androidx.room.ktx)
implementation(libs.androidx.room.paging)
testImplementation("junit:junit:4.12")
kapt(libs.androidx.room.room.compiler)

//Livedata
implementation(libs.androidx.lifecycle.livedata.ktx)

//PreferenceDataStore
implementation(libs.androidx.datastore.preferences)

//Glide
implementation(libs.glide)

//Retrofit
implementation (libs.retrofit)
implementation (libs.converter.gson)
implementation (libs.logging.interceptor)
implementation (libs.squareup.converter.moshi)
implementation (libs.moshi.kotlin)

//Paging
implementation (libs.androidx.paging.runtime.ktx)

//CameraX
implementation (libs.androidx.camera.core)
implementation (libs.androidx.camera.camera2)
implementation (libs.androidx.camera.lifecycle)
implementation (libs.androidx.camera.video)

implementation (libs.androidx.camera.view)
implementation (libs.androidx.camera.extensions)

androidTestImplementation(libs.androidx.junit)
androidTestImplementation(libs.androidx.espresso.core)
}