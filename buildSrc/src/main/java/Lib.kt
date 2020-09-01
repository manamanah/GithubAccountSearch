object Lib {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Version.core_ktx}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraint}"

    const val fragment = "androidx.fragment:fragment-ktx:${Version.fragment}"

    // MaterialDesign
    const val materialDesign = "com.google.android.material:material:${Version.material}"

    // ViewModel & LiveData
    const val lifeCycle = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle}"
    const val legacyV4 = "androidx.legacy:legacy-support-v4:${Version.legacy_support}"
    const val lifecyleAnnotation = "androidx.lifecycle:lifecycle-common-java8:${Version.lifecycle}"

    // Navigation
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"

    // Moshi
    const val moshi = "com.squareup.moshi:moshi:${Version.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"
    const val moshiKotlinAnnotation = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"

    // Retrofit2
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit2}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Version.retrofit2}"
    const val retrofitCoroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Version.retrofit2_kotlin_coroutines}"

    // Coroutines
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
}