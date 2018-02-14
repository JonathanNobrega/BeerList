package com.jonathannobrega.beerlist.presentation.beerdetails

import com.jonathannobrega.beerlist.presentation.common.BasePresenter

interface BeerDetailsContract {

    interface View {

        fun showBeerImage(imageUrl: String)

        fun showBeerName(beerName: String)

        fun showBeerDescription(description: String)

        fun showNotImplementedMessage()

        fun goToBeersScreen()
    }

    interface Presenter : BasePresenter<View> {

        fun onFavoriteClicked()
    }
}