plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(Config.compileSdkVersion)
    buildToolsVersion = Config.buildToolsVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdkVersion(Config.minSdkVersion)
        targetSdkVersion(Config.targetSdkVersion)
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Lib.kotlinStdLib)
    implementation(Lib.appCompat)
    implementation(Lib.coreKtx)
    implementation(Lib.constraintLayout)

    implementation(Lib.fragment)

    // MaterialDesign
    implementation(Lib.materialDesign)

    // ViewModel & LiveData
    implementation(Lib.lifeCycle)
    implementation(Lib.legacyV4)
    kapt(Lib.lifecyleAnnotation)

    // Navigation
    implementation(Lib.navigationFragment)
    implementation(Lib.navigationUI)

    // Moshi
    implementation(Lib.moshi)
    implementation(Lib.moshiKotlin)
    kapt(Lib.moshiKotlinAnnotation)

    // Retrofit2
    implementation(Lib.retrofit)
    implementation(Lib.retrofitMoshiConverter)
    implementation(Lib.retrofitCoroutinesAdapter)

    // Coroutines
    implementation(Lib.coroutines)
    implementation(Lib.coroutinesAndroid)

    // Glide
    implementation(Lib.glide)

    // UnitTests
    testImplementation(UnitTestLib.jUnit)
    testImplementation(UnitTestLib.archCore)
    testImplementation(UnitTestLib.coroutines)

    // DeviceTests
    androidTestImplementation(DeviceTestLib.jUnit)
    androidTestImplementation(DeviceTestLib.espressoCore)
}
