import Lib.implementCoroutines
import Lib.implementMoshi
import Lib.implementNavigation
import Lib.implementRetrofitWithMoshi
import Lib.implementViewModelLifecycle

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
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

        buildConfigField("String", "GIT_BASE_URL", Config.baseUrl)
    }

    buildTypes {
        getByName("debug"){
            isMinifyEnabled = false
            isDebuggable = true
        }

        getByName("release") {
            isMinifyEnabled = true
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
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        exclude("META-INF/core.kotlin_module")
        exclude("META-INF/specs.kotlin_module")
        exclude("META-INF/elements.kotlin_module")
        exclude("META-INF/metadata.kotlin_module")
        exclude("META-INF/metadata.jvm.kotlin_module")
        exclude("META-INF/kotlinx-metadata.kotlin_module")
        exclude("META-INF/kotlinx-metadata-jvm.kotlin_module")
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

    // Koin
    implementation(Lib.koin)

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
    testImplementation(TestLib.Unit.koin)

    // DeviceTests
    androidTestImplementation(TestLib.Device.jUnit)
    androidTestImplementation(TestLib.Device.espressoCore)
    androidTestImplementation(TestLib.Device.navigation)
}