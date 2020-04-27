package com.android.testapp.base.interactor

interface InteractorWithoutParams<out T> {
    suspend operator fun invoke(): T
}