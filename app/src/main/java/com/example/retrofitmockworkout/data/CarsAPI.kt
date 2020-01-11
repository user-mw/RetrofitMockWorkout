package com.example.retrofitmockworkout.data

import com.example.retrofitmockworkout.domain.Car
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CarsAPI {

    @GET("cars")
    fun getCars(): Single<List<Car>>

    @GET("cars/{id}")
    fun getCar(@Path("id") id: Int): Single<Car>
}