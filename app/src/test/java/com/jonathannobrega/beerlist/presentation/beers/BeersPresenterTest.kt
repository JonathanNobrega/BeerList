package com.jonathannobrega.beerlist.presentation.beers

import com.jonathannobrega.beerlist.domain.interactor.beer.GetBeersUseCase
import com.jonathannobrega.beerlist.domain.model.Beer
import com.jonathannobrega.beerlist.presentation.mapper.BeerMapper
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BeersPresenterTest {

    private lateinit var view: BeersContract.View
    private lateinit var mapper: BeerMapper
    private lateinit var getBeersUseCase: GetBeersUseCase

    private lateinit var beersPresenter: BeersPresenter
    private lateinit var captor: KArgumentCaptor<DisposableSingleObserver<List<Beer>>>

    @Before
    fun setup() {
        captor = argumentCaptor()
        view = mock()
        mapper = mock()
        getBeersUseCase = mock()
        beersPresenter = BeersPresenter(getBeersUseCase, mapper)
    }

    @Test
    fun retrieveBeersShowsErrorState() {
        beersPresenter.attachView(view)
        beersPresenter.retrieveBeers()
        verify(getBeersUseCase).execute(captor.capture(), eq(null))
        captor.firstValue.onError(Exception())
        verify(view).showErrorState()
    }
}