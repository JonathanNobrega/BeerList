package com.jonathannobrega.beerlist.data.network.datasource

import com.jonathannobrega.beerlist.domain.model.Beer
import com.jonathannobrega.beerlist.domain.repository.BeerRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * TODO: Create a real implementation
 */
class NetworkBeerDataSource @Inject constructor() : BeerRepository {

    private val TEMP_TEST_BEER = Beer("qwerty", "Beer name", "Active", "Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam eaque ipsa, quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt, explicabo. Nemo enim ipsam voluptatem, quia voluptas sit, aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos, qui ratione voluptatem sequi nesciunt, neque porro quisquam est, qui dolorem ipsum, quia dolor sit amet consectetur adipisci[ng] velit, sed quia non-numquam [do] eius modi tempora inci[di]dunt, ut labore et dolore magnam aliquam quaerat voluptatem.",
            "https://www.ebag.bg/en/product/17647/images/0/800")

    override fun getBeers(): Single<List<Beer>> {
        return Single.just(
                listOf(TEMP_TEST_BEER, TEMP_TEST_BEER)
        )
    }
}