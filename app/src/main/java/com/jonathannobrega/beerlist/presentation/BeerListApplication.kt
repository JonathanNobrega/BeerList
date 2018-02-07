package com.jonathannobrega.beerlist.presentation

import android.app.Application

import com.jonathannobrega.beerlist.presentation.injection.module.ApplicationModule

class BeerListApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        applicationModule = ApplicationModule(this)
    }

    // TODO: Fix this crap
    companion object {
        lateinit var applicationModule: ApplicationModule
    }
}
