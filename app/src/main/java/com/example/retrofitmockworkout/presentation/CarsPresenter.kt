package com.example.retrofitmockworkout.presentation

import com.example.retrofitmockworkout.domain.GetItemsUseCase
import com.example.retrofitmockworkout.domain.GetCarUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class CarsPresenter(
    val useCase: GetItemsUseCase,
    val getCarUseCase: GetCarUseCase
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
        getCarUseCase(mockCarId)
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