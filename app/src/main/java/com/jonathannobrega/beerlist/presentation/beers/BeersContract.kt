package com.jonathannobrega.beerlist.presentation.beers

import com.jonathannobrega.beerlist.presentation.common.BasePresenter
import com.jonathannobrega.beerlist.presentation.model.PresentationBeer

interface BeersContract {

    interface View {

        fun showProgress()

        fun hideProgress()

        fun showBeers(beers: List<PresentationBeer>)

        fun hideBeers()

        fun showErrorState()

        fun hideErrorState()

        fun goToBeerDetailsScreen(beer: PresentationBeer)
    }

    interface Presenter : BasePresenter<View> {

        fun retrieveBeers()

        fun onBeerSelected(beer: PresentationBeer)
    }
}
