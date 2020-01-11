package com.example.retrofitmockworkout.domain

import io.reactivex.Single

interface GetItemsUseCase {

    operator fun invoke(): Single<List<Car>>
}

class GetItemsUseCaseImpl(private val repository: CarsRepository): GetItemsUseCase {

    override fun invoke(): Single<List<Car>> {

        return repository.getCars()
    }
}