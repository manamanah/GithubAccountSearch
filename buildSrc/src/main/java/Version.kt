object Version {
    const val gradle = "7.2.1"
    const val kotlin = "1.7.10"
    const val core = "1.8.0"
    // allow access to new APIs on older API versions
    const val appcompat = "1.4.2"

    const val activity = "1.5.0"
    const val fragment = "1.5.0"

    const val constraint = "2.1.4"
    const val coroutines = "1.6.3"
    const val lifecycle = "2.5.0"
    const val material = "1.6.1"

    const val navigation = "2.5.0"

    // logging
    const val timber = "5.0.1"

    // di
    const val koin = "3.2.0"

    // network
    const val retrofit2_kotlin_coroutines = "0.9.2"
    const val retrofit2 = "2.9.0"

    const val moshi = "1.13.0"

    const val okhttp = "4.10.0"

    // not yet supports KSP, so staying with kapt
    const val glide = "4.13.2"

    // testing
    // todo when adding tests: unit/integr tests: JUnit5; instr. test Kaspresso
    const val junit = "4.13.2"
    const val test_junit = "1.1.2"
    const val core_testing = "2.1.0"
    const val espresso = "3.3.0"
}
