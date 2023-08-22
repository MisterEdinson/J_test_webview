buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.5.3")
    }
}


plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
}