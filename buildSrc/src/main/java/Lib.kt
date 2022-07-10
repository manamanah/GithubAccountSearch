object Lib {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appcompat}"
    const val core = "androidx.core:core-ktx:${Version.core}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraint}"

    const val activity = "androidx.activity:activity-ktx:${Version.activity}"
    const val fragment = "androidx.fragment:fragment-ktx:${Version.fragment}"

    // material design
    const val materialDesign = "com.google.android.material:material:${Version.material}"

    // viewModel & liveData
    const val viewModel =  "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"
    const val lifeCycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Version.lifecycle}"

    // koin
    const val koin = "io.insert-koin:koin-android:${Version.koin}"

    // navigation
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
    const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"

    // moshi
    const val moshi = "com.squareup.moshi:moshi:${Version.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"
    const val moshiKotlinAnnotation = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"

    // retrofit2
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit2}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Version.retrofit2}"
    const val retrofitCoroutinesAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Version.retrofit2_kotlin_coroutines}"


    // coroutines
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

    // glide
    const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
}