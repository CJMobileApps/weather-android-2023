package com.cjmobileapps.weatherandroid2023.testutil

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

//class TestCoroutineRule : TestRule {
//    //TODO these are deprecated fix this later
//    private val testCoroutineDispatcher = TestCoroutineDispatcher()
//    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    override fun apply(base: Statement, description: Description): Statement = object : Statement() {
//
//        override fun evaluate() {
//            Dispatchers.setMain(testCoroutineDispatcher)
//
//            base.evaluate()
//
//            Dispatchers.resetMain()
//            testCoroutineScope.cleanupTestCoroutines()
//        }
//    }
//}
