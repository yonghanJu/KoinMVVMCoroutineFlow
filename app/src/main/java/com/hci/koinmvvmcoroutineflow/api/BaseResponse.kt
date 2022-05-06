package com.hci.koinmvvmcoroutineflow.api

abstract class BaseResponse<M> {
    abstract fun mapper(): M
}