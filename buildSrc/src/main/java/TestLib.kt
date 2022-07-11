object TestLib {

    object Unit {
        const val core = "androidx.arch.core:core-testing:${Version.core_testing}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"
        const val jUnit = "junit:junit:${Version.junit}"
        // note: there is koin-test available but rather fake or mockk
    }

    object Device {
        const val jUnit = "androidx.test.ext:junit:${Version.test_junit}"
        const val espressoCore = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val navigation = "androidx.navigation:navigation-testing:${Version.navigation}"
        const val fragmentInIsolation = "androidx.fragment:fragment-testing:${Version.fragment}"
    }
}