package com.example.retrofitmockworkout.custom_di

import com.example.retrofitmockworkout.data.CarsDataSource
import com.example.retrofitmockworkout.data.CarsRepositoryImpl
import com.example.retrofitmockworkout.domain.GetItemsUseCaseImpl
import com.example.retrofitmockworkout.domain.GetCarUseCaseImpl
import com.example.retrofitmockworkout.presentation.CarsPresenter

class BadPresenterFactory {

    // Created just because there is no Dagger
    companion object {
        fun create(): CarsPresenter =
            CarsPresenter(GetItemsUseCaseImpl(CarsRepositoryImpl(CarsDataSource())), GetCarUseCaseImpl())
    }
}