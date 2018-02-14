package com.jonathannobrega.beerlist.domain.interactor.beer

import com.jonathannobrega.beerlist.domain.executor.PostExecutionThread
import com.jonathannobrega.beerlist.domain.executor.ThreadExecutor
import com.jonathannobrega.beerlist.domain.interactor.SingleUseCase
import com.jonathannobrega.beerlist.domain.model.Beer
import com.jonathannobrega.beerlist.domain.repository.BeerRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Use case used for retrieving a [Beer] from the [BeerRepository] using its id.
 */
open class GetBeerByIdUseCase @Inject constructor(
        private val beerRepository: BeerRepository,
        threadExecutor: ThreadExecutor,
        postExecutionThread: PostExecutionThread
) : SingleUseCase<Beer, GetBeerByIdUseCase.Params>(threadExecutor, postExecutionThread) {

    override fun buildUseCaseObservable(params: Params): Single<Beer> {
        return beerRepository.getBeerById(params.beerId)
    }

    /**
     * A [Params] class used to define parameters that can be supplied
     * for [GetBeersUseCase.execute].
     */
    class Params(val beerId: Long)
}