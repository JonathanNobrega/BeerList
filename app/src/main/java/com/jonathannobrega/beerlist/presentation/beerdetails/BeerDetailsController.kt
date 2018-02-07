package com.jonathannobrega.beerlist.presentation.beerdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jonathannobrega.beerlist.R
import com.jonathannobrega.beerlist.presentation.common.BaseController
import com.jonathannobrega.beerlist.presentation.extensions.inflate

class BeerDetailsController : BaseController {

    private var beerId: String

    constructor(bundle: Bundle) : super(bundle) {
        this.beerId = bundle.getString(KEY_BEER_ID)
    }

    constructor(beerId: String) : this(Bundle().apply {
        putString(KEY_BEER_ID, beerId)
    })

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        beerId = savedInstanceState.getString(KEY_BEER_ID)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return container.inflate(R.layout.controller_beer_details)
    }

    /********** Companion object **********/

    companion object {
        private const val KEY_BEER_ID = "KEY_BEER_ID"
    }
}