package com.hci.koinmvvmcoroutineflow.DI

import org.koin.dsl.module

object AppModules {
    private val repositories = module {}
    private val viewModels = module {}
    private val etc = module {}

    val modules = listOf(etc, repositories, viewModels)
}