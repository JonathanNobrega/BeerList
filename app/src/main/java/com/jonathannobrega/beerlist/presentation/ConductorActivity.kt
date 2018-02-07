package com.jonathannobrega.beerlist.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.jonathannobrega.beerlist.R
import com.jonathannobrega.beerlist.presentation.beers.BeersController
import kotlinx.android.synthetic.main.activity_conductor.*

class ConductorActivity : AppCompatActivity() {

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conductor)

        router = Conductor.attachRouter(this, frameLayoutViewContainer, savedInstanceState)

        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(BeersController()))
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }
}
