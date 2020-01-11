package com.example.retrofitmockworkout.domain

import io.reactivex.Single

interface CarsRepository {

    fun getCars(): Single<List<Car>>

    fun getCar(id: Int): Single<Car>
}