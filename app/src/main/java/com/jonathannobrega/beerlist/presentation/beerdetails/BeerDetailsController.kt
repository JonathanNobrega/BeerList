package com.jonathannobrega.beerlist.presentation.beerdetails

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.jonathannobrega.beerlist.R
import com.jonathannobrega.beerlist.presentation.common.BaseController
import com.jonathannobrega.beerlist.presentation.extensions.getViewOrThrow
import com.jonathannobrega.beerlist.presentation.extensions.inflate
import com.jonathannobrega.beerlist.presentation.injection.component.DaggerBeerDetailsComponent
import com.jonathannobrega.beerlist.presentation.injection.module.BeerDetailsControllerModule
import kotlinx.android.synthetic.main.controller_beer_details.view.*
import javax.inject.Inject

class BeerDetailsController : BaseController, BeerDetailsContract.View {

    @Inject
    lateinit var presenter: BeerDetailsContract.Presenter

    constructor(bundle: Bundle) : super(bundle) {
        val beerId = bundle.getLong(KEY_BEER_ID)

        DaggerBeerDetailsComponent.builder()
                .beerDetailsControllerModule(BeerDetailsControllerModule(beerId))
                .build()
                .inject(this)
    }

    constructor(beerId: Long) : this(Bundle().apply {
        putLong(KEY_BEER_ID, beerId)
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = container.inflate(R.layout.controller_beer_details)
        initializeToolbar(view.toolbar)
        initializeClickListeners(view)
        return view
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        presenter.attachView(this)
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        presenter.onDetachView()
    }

    /********** BeerDetailsContract.View **********/

    override fun showBeerImage(imageUrl: String) {
        Glide.with(getViewOrThrow().context)
                .load(imageUrl)
                .transition(withCrossFade())
                .into(getViewOrThrow().imageViewBeerImage)
    }

    override fun showBeerName(beerName: String) {
        getViewOrThrow().textViewBeerName.text = beerName
    }

    override fun showBeerDescription(description: String) {
        getViewOrThrow().textViewBeerDescription.text = description
    }

    override fun showNotImplementedMessage() {
        Snackbar.make(getViewOrThrow(), "Not implemented", Snackbar.LENGTH_SHORT).show()
    }

    override fun goToBeersScreen() {
        router.popCurrentController()
    }

    /********** Methods **********/

    private fun initializeToolbar(toolbar: Toolbar) {
        toolbar.setNavigationOnClickListener { router.popCurrentController() }
    }

    private fun initializeClickListeners(view: View) {
        view.fabFavoriteBeer.setOnClickListener { presenter.onFavoriteClicked() }
    }

    /********** Companion object **********/

    companion object {
        private const val KEY_BEER_ID = "KEY_BEER_ID"
    }
}