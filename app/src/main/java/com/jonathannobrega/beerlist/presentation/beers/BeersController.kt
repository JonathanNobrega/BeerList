package com.jonathannobrega.beerlist.presentation.beers

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.jonathannobrega.beerlist.R
import com.jonathannobrega.beerlist.presentation.beerdetails.BeerDetailsController
import com.jonathannobrega.beerlist.presentation.common.BaseController
import com.jonathannobrega.beerlist.presentation.extensions.getViewOrThrow
import com.jonathannobrega.beerlist.presentation.extensions.hide
import com.jonathannobrega.beerlist.presentation.extensions.inflate
import com.jonathannobrega.beerlist.presentation.extensions.show
import com.jonathannobrega.beerlist.presentation.injection.component.DaggerBeersComponent
import com.jonathannobrega.beerlist.presentation.model.PresentationBeer
import kotlinx.android.synthetic.main.controller_beers.view.*
import kotlinx.android.synthetic.main.generic_error_placeholder.view.*
import javax.inject.Inject

class BeersController @Inject constructor() : BaseController(),
        BeersContract.View, BeersAdapter.ItemListener {

    @Inject
    lateinit var presenter: BeersContract.Presenter
    lateinit var beersAdapter: BeersAdapter

    override fun onContextAvailable(context: Context) {
        DaggerBeersComponent.builder().build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = container.inflate(R.layout.controller_beers)
        initializeClickListeners(view)
        initializeSwipeRefresh(view.swipeRefresh)
        initializeRecyclerView(view.recyclerViewBeers)
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

    /********** BeersContract.View **********/

    override fun showProgress() {
        getViewOrThrow().swipeRefresh.isRefreshing = true
    }

    override fun hideProgress() {
        getViewOrThrow().swipeRefresh.isRefreshing = false
    }

    override fun showBeers(beers: List<PresentationBeer>) {
        getViewOrThrow().recyclerViewBeers.show()
        beersAdapter.refreshData(beers)
    }

    override fun hideBeers() {
        getViewOrThrow().recyclerViewBeers.hide()
    }

    override fun showErrorState() {
        getViewOrThrow().linearLayoutErrorPlaceholderContainer.show()
    }

    override fun hideErrorState() {
        getViewOrThrow().linearLayoutErrorPlaceholderContainer.hide()
    }

    override fun goToBeerDetailsScreen(beer: PresentationBeer) {
        router.pushController(
                RouterTransaction.with(BeerDetailsController(beer.id))
                        .pushChangeHandler(FadeChangeHandler())
                        .popChangeHandler(FadeChangeHandler())
        )
    }

    /********** BeersAdapter.ItemListener **********/

    override fun onBeerClicked(beer: PresentationBeer) {
        presenter.onBeerSelected(beer)
    }

    /********** Methods **********/

    private fun initializeClickListeners(view: View) {
        view.buttonErrorRetry.setOnClickListener { presenter.retrieveBeers() }
    }

    private fun initializeSwipeRefresh(swipeRefresh: SwipeRefreshLayout) {
        swipeRefresh.setColorSchemeResources(R.color.color_primary)
        swipeRefresh.setOnRefreshListener { presenter.retrieveBeers() }
    }

    private fun initializeRecyclerView(recyclerView: RecyclerView) {
        beersAdapter = BeersAdapter(this)
        with(recyclerView) {
            layoutManager = LinearLayoutManager(recyclerView.context)
            adapter = beersAdapter
        }
    }
}
