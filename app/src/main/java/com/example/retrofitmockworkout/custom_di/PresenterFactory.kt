package com.example.retrofitmockworkout.custom_di

import com.example.retrofitmockworkout.presentation.CarsPresenter

interface PresenterFactory {

    fun create(): CarsPresenter
}