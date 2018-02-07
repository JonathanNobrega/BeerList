package com.jonathannobrega.beerlist.domain.interactor.beer

import com.jonathannobrega.beerlist.domain.model.Beer
import com.jonathannobrega.beerlist.domain.executor.PostExecutionThread
import com.jonathannobrega.beerlist.domain.executor.ThreadExecutor
import com.jonathannobrega.beerlist.domain.repository.BeerRepository
import io.reactivex.Single
import com.jonathannobrega.beerlist.domain.interactor.SingleUseCase
import javax.inject.Inject

/**
 * Use case used for retrieving a [List] of [Beer] from the [BeerRepository]
 */
open class GetBeersUseCase @Inject constructor(
        private val beerRepository: BeerRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Beer>, Void?>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Void?): Single<List<Beer>> {
        return beerRepository.getBeers()
    }
}