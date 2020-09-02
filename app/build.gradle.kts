import Lib.implementCoroutines
import Lib.implementMoshi
import Lib.implementNavigation
import Lib.implementRetrofitWithMoshi
import Lib.implementViewModelLifecycle

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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "proguard-retrofit2-rules.pro",
                "proguard-okio-rules-pro"
            )
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

    packagingOptions {
        exclude("META-INF/metadata.kotlin_module")
        exclude("META-INF/metadata.jvm.kotlin_module")
    }
}

dependencies {
    implementation(Lib.kotlinStdLib)

    implementation(Lib.appCompat)
    implementation(Lib.coreKtx)

    implementation(Lib.constraintLayout)
    implementation(Lib.activityArtifact)
    implementation(Lib.fragmentArtifact)

    // MaterialDesign
    implementation(Lib.materialDesign)

    // ViewModel & LiveData
    implementViewModelLifecycle()

    // Navigation
    implementNavigation()

    // Moshi
    implementMoshi()

    // Retrofit2
    implementRetrofitWithMoshi()

    // Coroutines
    implementCoroutines()

    // Glide
    implementation(Lib.glide)

    // UnitTests
    testImplementation(TestLib.Unit.jUnit)
    testImplementation(TestLib.Unit.core)
    testImplementation(TestLib.Unit.coroutines)

    // DeviceTests
    androidTestImplementation(TestLib.Device.jUnit)
    androidTestImplementation(TestLib.Device.espressoCore)
    androidTestImplementation(TestLib.Device.navigation)
}
