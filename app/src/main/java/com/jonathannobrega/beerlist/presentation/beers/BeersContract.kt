package com.jonathannobrega.beerlist.presentation.beers

import com.jonathannobrega.beerlist.presentation.common.BasePresenter
import com.jonathannobrega.beerlist.presentation.model.BeerViewModel

interface BeersContract {

    interface View {

        fun showProgress()

        fun hideProgress()

        fun showBeers(beers: List<BeerViewModel>)

        fun hideBeers()

        fun showErrorState()

        fun hideErrorState()

        fun goToBeerDetailsScreen(beer: BeerViewModel)
    }

    interface Presenter : BasePresenter<View> {

        fun retrieveBeers()

        fun onBeerSelected(beer: BeerViewModel)
    }
}
