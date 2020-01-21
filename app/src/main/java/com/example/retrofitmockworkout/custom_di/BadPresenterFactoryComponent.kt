package com.example.retrofitmockworkout.custom_di

import android.content.Context

class BadPresenterFactoryComponent(private val context: Context) {

    fun inject(factoryInjector: PresenterFactoryInjector) {
        factoryInjector.inject(BadPresenterFactory(context))
    }
}