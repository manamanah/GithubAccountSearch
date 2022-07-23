plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("org.jlleitschuh.gradle.ktlint") version Version.ktlint
}

android {
    compileSdk = Config.targetSdkVersion
    buildToolsVersion = Config.buildToolsVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName
        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
                "proguard-retrofit2-rules.pro",
                "proguard-glide-rules.pro",
                "proguard-okio-rules-pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    packagingOptions {
        resources.excludes += "DebugProbesKt.bin"
        resources.excludes += "META-INF/gradle/incremental.annotation.processors"
    }
}

dependencies {
    implementation(Lib.kotlin)

    implementation(Lib.appCompat)
    implementation(Lib.core)

    implementation(Lib.constraintLayout)
    implementation(Lib.activity)
    implementation(Lib.fragment)

    // material design
    implementation(Lib.materialDesign)

    // viewModel & lifecycle
    implementation(Lib.viewModel)
    implementation(Lib.lifeCycleCompiler)

    // logging
    implementation(Lib.timber)

    // koin
    implementation(Lib.koin)

    // navigation
    implementation(Lib.navigation)
    implementation(Lib.navigationUI)

    // moshi
    implementation(Lib.moshi)
    implementation(Lib.moshiKotlin)
    implementation(Lib.moshiKotlinAnnotation)

    // retrofit2
    implementation(Lib.retrofit)
    implementation(Lib.retrofitMoshiConverter)
    implementation(Lib.retrofitCoroutinesAdapter)

    // okhttp + logging
    implementation(Lib.okhttp)
    implementation(Lib.okhttpLoggingInterceptor)

    // coroutines
    implementation(Lib.coroutines)
    implementation(Lib.coroutinesAndroid)

    // glide
    implementation(Lib.glide)
    kapt(Lib.glideCompile)

    // unit/instr tests
    testImplementation(TestLib.Unit.jUnit)
    testImplementation(TestLib.Unit.core)
    testImplementation(TestLib.Unit.coroutines)

    // on device tests
    androidTestImplementation(TestLib.Device.jUnit)
    androidTestImplementation(TestLib.Device.espressoCore)
    androidTestImplementation(TestLib.Device.navigation)

    debugImplementation(TestLib.Device.fragmentInIsolation)
}

// run ktlint format (auto-fix errors + report) on every build
tasks.getByPath(":app:preBuild").dependsOn("ktlintFormat")
