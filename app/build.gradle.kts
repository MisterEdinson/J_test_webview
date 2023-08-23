plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("androidx.navigation.safeargs.kotlin")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.j_test_webview"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.j_test_webview"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //Volley
    implementation ("com.android.volley:volley:1.2.1")
    //Navigation
    val navVersion = "2.5.3"
    implementation ("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$navVersion")
    //Hilt
    val hiltVersion = "2.44.2"
    implementation ("com.google.dagger:hilt-android:$hiltVersion")
    kapt ("com.google.dagger:hilt-compiler:$hiltVersion")
    //Gson
    implementation ("com.google.code.gson:gson:2.8.8")
}
kapt {
    correctErrorTypes = true
}