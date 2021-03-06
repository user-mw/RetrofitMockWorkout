package com.example.retrofitmockworkout.presentation

import com.example.retrofitmockworkout.domain.GetCarsUseCase
import com.example.retrofitmockworkout.domain.GetSingleCarUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class CarsPresenter(
    private val useCase: GetCarsUseCase,
    private val getSingleCarUseCase: GetSingleCarUseCase
    ) {

    private val compositeDisposable = CompositeDisposable()
    private var view: CarsView? = null

    fun onAttach(view: CarsView) {
        this.view = view
        loadData()
    }

    private fun loadData() {
        useCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { items -> view?.showItems(items) },
                { view?.showEmptyMessage() }
            )
            .addTo(compositeDisposable)
    }

    fun onItemClick(mockCarId: Int) {
        getSingleCarUseCase(mockCarId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { mockCar -> view?.showMessage(mockCar.brand) },
                { error -> view?.showMessage(error.message ?: "") }
            )
            .addTo(compositeDisposable)
    }

    fun onDetach() {
        compositeDisposable.clear()
        view = null
    }
}