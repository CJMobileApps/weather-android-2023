package com.cjmobileapps.weatherandroid2023.util.coroutine

import kotlinx.coroutines.Dispatchers

object CoroutineDispatchersImpl : CoroutineDispatchers {
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
}
