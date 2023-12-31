object Libraries {

  // Support
  const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
  const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
  const val androidSupport = "androidx.legacy:legacy-support-v4:${Versions.supportVersion}"

  // Kotlin
  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

  // Arch Components
  const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
  const val lifeData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
  const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
  const val viewModelState =
    "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
  const val roomVersion = "androidx.room:room-runtime:${Versions.roomVersion}"
  const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
  const val roomktx = "androidx.room:room-ktx:${Versions.roomVersion}"
  const val roomCommon = "androidx.room:room-common:${Versions.roomVersion}"

  // Kotlin Coroutines
  const val coroutinesCore =
    "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
  const val coroutinesAndroid =
    "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"

  // Networking
  const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
  const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
  const val gson = "com.google.code.gson:gson:${Versions.gson}"
  const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
  const val chuckLogging = "com.readystatesoftware.chuck:library:${Versions.chuckLogging}"
  const val alerter = "com.github.tapadoo:alerter:${Versions.alerter}"

  //work-manager
  const val workManager = "androidx.work:work-runtime-ktx:${Versions.workVersion}"
  const val hiltWorkManager = "androidx.hilt:hilt-work:${Versions.hiltWorkVersion}"
  const val hiltCommonWorkManager = "androidx.hilt:hilt-common:${Versions.hiltWorkVersion}"
  const val hiltCompilerWorkManager = "androidx.hilt:hilt-compiler:${Versions.hiltWorkVersion}"


  //pinView
  const val pinView = "com.chaos.view:pinview:1.4.4"

  //Image
  const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
  const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"


  // UI
  const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
  const val navigationFragment =
    "androidx.navigation:navigation-fragment-ktx:${Versions.androidNavigation}"
  const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Versions.androidNavigation}"
  const val loadingAnimations = "com.airbnb.android:lottie:${Versions.loadingAnimations}"

  // Utils
  const val playServices = "com.google.android.gms:play-services-auth:${Versions.playServices}"
  const val multidex = "androidx.multidex:multidex:${Versions.multidex}"

  // Hilt
  const val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
  const val hiltDaggerCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"

}