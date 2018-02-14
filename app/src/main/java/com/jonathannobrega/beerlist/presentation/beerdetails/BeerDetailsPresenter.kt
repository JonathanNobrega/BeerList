package com.jonathannobrega.beerlist.presentation.beerdetails

import com.jonathannobrega.beerlist.domain.interactor.beer.GetBeerByIdUseCase
import com.jonathannobrega.beerlist.domain.model.Beer
import com.jonathannobrega.beerlist.presentation.mapper.PresentationBeerMapper
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class BeerDetailsPresenter @Inject constructor(
        private val beerId: Long,
        private val getBeerByIdUseCase: GetBeerByIdUseCase,
        private val beerMapper: PresentationBeerMapper
) : BeerDetailsContract.Presenter {

    private var view: BeerDetailsContract.View? = null

    /********** BeerDetailsContract.Presenter **********/

    override fun attachView(view: BeerDetailsContract.View) {
        this.view = view
        retrieveBeer()
    }

    override fun onFavoriteClicked() {
        getViewOrThrow().showNotImplementedMessage()
    }

    override fun onDetachView() {
        getBeerByIdUseCase.dispose()
        view = null
    }

    /********** Methods **********/

    // TODO: Move to a BasePresenter
    private fun getViewOrThrow(): BeerDetailsContract.View {
        val view = view
        if (view == null) {
            throw IllegalStateException("View not attached to presenter yet")
        } else {
            return view
        }
    }

    private fun retrieveBeer() {
        getBeerByIdUseCase.execute(GetBeerByIdSubscriber(), GetBeerByIdUseCase.Params(beerId))
    }

    /********** Inner classes **********/

    inner class GetBeerByIdSubscriber : DisposableSingleObserver<Beer>() {

        override fun onSuccess(beer: Beer) {
            val presentationBeer = beerMapper.mapFromDomainToPresentation(beer)
            with(getViewOrThrow()) {
                showBeerImage(presentationBeer.imageUrl)
                showBeerName(presentationBeer.name)
                showBeerDescription(presentationBeer.description)
            }
        }

        override fun onError(exception: Throwable) {
            // TODO: Handle exception
        }
    }
}