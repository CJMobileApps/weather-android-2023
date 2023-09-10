package com.cjmobileapps.weatherandroid2023.testutil

import com.cjmobileapps.weatherandroid2023.util.coroutine.CoroutineDispatchers
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

object TestCoroutineDispatchers : CoroutineDispatchers{

    override val io: CoroutineContext = Dispatchers.Unconfined

    override val main: CoroutineContext = Dispatchers.Unconfined
}
