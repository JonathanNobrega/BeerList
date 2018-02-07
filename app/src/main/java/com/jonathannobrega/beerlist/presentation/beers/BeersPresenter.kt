package com.jonathannobrega.beerlist.presentation.beers

import com.jonathannobrega.beerlist.domain.interactor.beer.GetBeersUseCase
import com.jonathannobrega.beerlist.domain.model.Beer
import com.jonathannobrega.beerlist.presentation.mapper.BeerMapper
import com.jonathannobrega.beerlist.presentation.model.BeerViewModel
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class BeersPresenter @Inject constructor(
        private val getBeersUseCase: GetBeersUseCase,
        private val beerMapper: BeerMapper
) : BeersContract.Presenter {

    private var view: BeersContract.View? = null

    /********** BeersContract.Presenter **********/

    override fun attachView(view: BeersContract.View) {
        this.view = view
        retrieveBeers()
    }

    override fun retrieveBeers() {
        getViewOrThrow().showProgress()
        getBeersUseCase.execute(GetBeersSubscriber())
    }


    override fun onBeerSelected(beer: BeerViewModel) {
        getViewOrThrow().goToBeerDetailsScreen(beer)
    }

    override fun onDetachView() {
        getBeersUseCase.dispose()
        view = null
    }

    /********** Methods **********/

    private fun getViewOrThrow(): BeersContract.View {
        val view = view
        if (view == null) {
            throw IllegalStateException("View not attached to presenter yet")
        } else {
            return view
        }
    }

    /********** Inner classes **********/

    inner class GetBeersSubscriber : DisposableSingleObserver<List<Beer>>() {

        override fun onSuccess(beers: List<Beer>) {
            val beersViewModel = beers.map { beerMapper.mapFromDomainToViewModel(it) }
            getViewOrThrow().hideProgress()
            getViewOrThrow().hideErrorState()
            getViewOrThrow().showBeers(beersViewModel)
        }

        override fun onError(exception: Throwable) {
            getViewOrThrow().hideProgress()
            getViewOrThrow().hideBeers()
            getViewOrThrow().showErrorState()
        }
    }
}