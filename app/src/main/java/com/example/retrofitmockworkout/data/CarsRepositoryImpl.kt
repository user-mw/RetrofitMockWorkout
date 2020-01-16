package com.example.retrofitmockworkout.data

import com.example.retrofitmockworkout.domain.CarsRepository
import com.example.retrofitmockworkout.domain.Car
import io.reactivex.Single

class CarsRepositoryImpl(private val carsDataSource: CarsDataSource) : CarsRepository {

    override fun getCars(): Single<List<Car>> =
        carsDataSource.getCars()

    override fun getCar(id: Int): Single<Car> =
        carsDataSource.getCar(id)
}