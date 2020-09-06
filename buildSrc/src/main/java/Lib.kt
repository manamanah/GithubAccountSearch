import org.gradle.api.artifacts.dsl.DependencyHandler

object Lib {
    private const val implementation = "implementation"

    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"
    const val appCompat = "androidx.appcompat:appcompat:${Version.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Version.core_ktx}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraint}"

    const val activityArtifact = "androidx.activity:activity-ktx:${Version.activityArtifact}"
    const val fragmentArtifact = "androidx.fragment:fragment-ktx:${Version.fragmentArtifact}"

    // MaterialDesign
    const val materialDesign = "com.google.android.material:material:${Version.material}"

    // region viewModel & liveData
    // ViewModel
    private const val viewModel =  "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    // LiveData
    private const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"
    private const val lifecycleAnnotation = "androidx.lifecycle:lifecycle-common-java8:${Version.lifecycle}"

    fun DependencyHandler.implementViewModelLifecycle() {
        add(implementation, viewModel)
        add(implementation, liveData)
        add(implementation, lifecycleAnnotation)
    }
    // endregion

    // Koin
    const val koin = "org.koin:koin-android:${Version.koin}"

    // region Navigation
    private const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
    private const val navigationUI = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"

    fun DependencyHandler.implementNavigation() {
        add(implementation, navigationFragment)
        add(implementation, navigationUI)
    }
    // endregion

    // region Moshi
    private const val moshi = "com.squareup.moshi:moshi:${Version.moshi}"
    private const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"
    private const val moshiKotlinAnnotation = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"

    fun DependencyHandler.implementMoshi() {
        add(implementation, moshi)
        add(implementation, moshiKotlin)
        add(implementation, moshiKotlinAnnotation)
    }
    // endregion

    // region Retrofit2
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit2}"
    private const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Version.retrofit2}"
    private const val retrofitCoroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Version.retrofit2_kotlin_coroutines}"

    fun DependencyHandler.implementRetrofitWithMoshi() {
        add(implementation, retrofit)
        add(implementation, retrofitMoshiConverter)
        add(implementation, retrofitCoroutinesAdapter)
    }
    // endregion

    // region Coroutines
    private const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    private const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

    fun DependencyHandler.implementCoroutines() {
        add(implementation, coroutines)
        add(implementation, coroutinesAndroid)
    }
    // endregion

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
}