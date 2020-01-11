package com.example.retrofitmockworkout.presentation

import com.example.retrofitmockworkout.domain.Car

interface CarsView {

    fun showItems(cars: List<Car>)

    fun showEmptyMessage()

    fun showMessage(title: String)
}