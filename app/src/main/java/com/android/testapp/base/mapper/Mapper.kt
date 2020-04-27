package com.android.testapp.base.mapper

interface Mapper<F, T> {
    suspend fun map(from: F): T

    suspend fun map(from: List<F>): List<T> {
        return MutableList(from.size) {
            map(from[it])
        }
    }
}