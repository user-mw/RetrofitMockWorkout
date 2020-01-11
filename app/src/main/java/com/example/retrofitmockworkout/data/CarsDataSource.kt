package com.example.retrofitmockworkout.data

import com.example.retrofitmockworkout.domain.Car
import io.reactivex.Single

class CarsDataSource(private val carsApi: CarsAPI = RetrofitProvider().provideApi()) {

    fun getCars(): Single<List<Car>> {
        return carsApi.getCars()
    }

    fun getCar(id: Int): Single<Car> = carsApi.getCar(id)
}