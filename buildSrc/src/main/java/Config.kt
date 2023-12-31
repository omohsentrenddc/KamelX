object Config {
  object AppConfig {
    const val appId = "app.fawry.task"
    const val compileSdkVersion = 31
    const val minSdkVersion = 23
    const val versionCode = 1
    const val versionName = "0.0.1"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val coreKtx =  "androidx.core:core-ktx:1.6.0"
  }

  object Dependencies {
    const val jitPackURL = "https://jitpack.io"
    const val gradleVersion = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafeArgs =
      "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidNavigation}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
    const val google_services = "com.google.gms:google-services:${Versions.google_services}"
    const val proto_buf = "com.google.protobuf:protobuf-gradle-plugin:${Versions.classPath_protobuf}"
  }

  object Plugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val navigationSafeArgs = "androidx.navigation.safeargs"
    const val hilt = "dagger.hilt.android.plugin"
    const val ktLint = "org.jlleitschuh.gradle.ktlint"
    const val google_services = "com.google.gms.google-services"
    const val proto_buf = "com.google.protobuf"
    const val extensions = "kotlin-android-extensions"
  }

  object Environments {
    const val roomDb = "\"fawry_db\""
    const val debugBaseUrl = "\"https://camelx.trenddc.com/api/\""
    const val releaseBaseUrl = "\"https://camelx.trenddc.com/api/\""
    const val APIKEY = "\"c50f5aa4e7c95a2a553d29b81aad6dd0\""
  }
}