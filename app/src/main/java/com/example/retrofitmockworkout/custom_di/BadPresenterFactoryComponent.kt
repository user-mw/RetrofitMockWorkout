package com.example.retrofitmockworkout.custom_di

class BadPresenterFactoryComponent {

    fun inject(factoryInjector: PresenterFactoryInjector) {
        factoryInjector.inject(BadPresenterFactory())
    }
}