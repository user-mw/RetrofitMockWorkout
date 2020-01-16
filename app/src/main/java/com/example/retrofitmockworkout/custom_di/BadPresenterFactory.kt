package com.example.retrofitmockworkout.custom_di

import com.example.retrofitmockworkout.data.CarsDataSource
import com.example.retrofitmockworkout.data.CarsRepositoryImpl
import com.example.retrofitmockworkout.data.RetrofitProvider
import com.example.retrofitmockworkout.domain.GetCarsUseCaseImpl
import com.example.retrofitmockworkout.domain.GetSingleCarUseCaseImpl
import com.example.retrofitmockworkout.presentation.CarsPresenter

// Created just because there is no Dagger
class BadPresenterFactory : PresenterFactory {

    private val carsApi = RetrofitProvider().provideApi()
    private val repository = CarsRepositoryImpl(CarsDataSource(carsApi))
    private val getCarsUseCase = GetCarsUseCaseImpl(repository)
    private val getSingleCarUseCase = GetSingleCarUseCaseImpl(repository)

    override fun create(): CarsPresenter =
        CarsPresenter(getCarsUseCase, getSingleCarUseCase)
}