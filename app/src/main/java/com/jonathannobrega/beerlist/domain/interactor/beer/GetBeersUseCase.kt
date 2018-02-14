package com.jonathannobrega.beerlist.domain.interactor.beer

import com.jonathannobrega.beerlist.domain.executor.PostExecutionThread
import com.jonathannobrega.beerlist.domain.executor.ThreadExecutor
import com.jonathannobrega.beerlist.domain.interactor.SingleUseCase
import com.jonathannobrega.beerlist.domain.model.Beer
import com.jonathannobrega.beerlist.domain.repository.BeerRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Use case used for retrieving a [List] of [Beer]s from the [BeerRepository].
 */
open class GetBeersUseCase @Inject constructor(
        private val beerRepository: BeerRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Beer>, GetBeersUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Single<List<Beer>> {
        return beerRepository.getBeers(params.page, params.perPage, params.searchQuery)
    }

    /**
     * A [Params] class used to define parameters that can be supplied
     * for [GetBeersUseCase.execute].
     */
    class Params(val page: Int, val perPage: Int = 30, val searchQuery: String? = null)
}