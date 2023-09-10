package com.cjmobileapps.weatherandroid2023.util.coroutine

import kotlin.coroutines.CoroutineContext

interface CoroutineDispatchers {
    val io: CoroutineContext
    val main: CoroutineContext
}
