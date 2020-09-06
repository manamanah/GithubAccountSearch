object TestLib {

    object Unit {
        const val core = "androidx.arch.core:core-testing:${Version.core_testing}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"
        const val jUnit = "junit:junit:${Version.junit}"
        const val koin = "org.koin:koin-test:${Version.koin}"
    }

    object Device {
        const val jUnit = "androidx.test.ext:junit:${Version.test_junit}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val navigation = "androidx.navigation:navigation-testing:${Version.navigation}"
    }
}