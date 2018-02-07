package com.jonathannobrega.beerlist.presentation.common

interface BasePresenter<in T> {

    fun attachView(view: T)

    fun onDetachView()
}
