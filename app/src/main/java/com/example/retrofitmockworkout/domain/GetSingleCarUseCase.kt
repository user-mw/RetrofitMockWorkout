package com.example.retrofitmockworkout.domain

import io.reactivex.Single

interface GetSingleCarUseCase {

    operator fun invoke(id: Int): Single<Car>
}

class GetSingleCarUseCaseImpl(private val repository: CarsRepository) : GetSingleCarUseCase {

    override fun invoke(id: Int): Single<Car> =
        repository.getCar(id)
}