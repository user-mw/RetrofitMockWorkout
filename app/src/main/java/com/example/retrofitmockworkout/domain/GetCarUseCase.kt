package com.example.retrofitmockworkout.domain

import com.example.retrofitmockworkout.data.CarsDataSource
import com.example.retrofitmockworkout.data.CarsRepositoryImpl
import io.reactivex.Single

interface GetCarUseCase {

    operator fun invoke(id: Int): Single<Car>
}

class GetCarUseCaseImpl(private val repository: CarsRepository = CarsRepositoryImpl(CarsDataSource())) : GetCarUseCase {

    override fun invoke(id: Int): Single<Car> =
        repository.getCar(id)
}