package com.jonathannobrega.beerlist.presentation.mapper

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer layers
 */
interface Mapper<out ViewModel, in DomainModel> {

    fun mapFromDomainToViewModel(type: DomainModel): ViewModel
}