package com.example.retrofitmockworkout.domain

import io.reactivex.Single

interface GetCarsUseCase {

    operator fun invoke(): Single<List<Car>>
}

class GetCarsUseCaseImpl(private val repository: CarsRepository): GetCarsUseCase {

    override fun invoke(): Single<List<Car>> =
        repository.getCars()
}