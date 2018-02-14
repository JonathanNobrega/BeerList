package com.jonathannobrega.beerlist.presentation

import android.app.Application
import com.jonathannobrega.beerlist.presentation.injection.component.ApplicationComponent
import com.jonathannobrega.beerlist.presentation.injection.component.DaggerApplicationComponent
import io.realm.Realm

class BeerListApplication : Application() {

    private lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        initializeInjector()
    }

    private fun initializeInjector() {
        appComponent = DaggerApplicationComponent.builder().build()
        appComponent.inject(this)
    }

    fun getAppComponent(): ApplicationComponent {
        return appComponent
    }
}