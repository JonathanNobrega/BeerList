package com.jonathannobrega.beerlist.presentation.injection.module

import com.jonathannobrega.beerlist.data.datasource.BeerRemote
import com.jonathannobrega.beerlist.presentation.injection.scope.PerApplication
import com.jonathannobrega.beerlist.remote.datasource.RemoteBeerDataSource
import com.jonathannobrega.beerlist.remote.mapper.RemoteBeerMapper
import com.jonathannobrega.beerlist.remote.service.BeerService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule {

    private val API_BASE_URL = "https://api.punkapi.com/v2/"

    @Provides
    @PerApplication
    fun provideBeerRemote(beerService: BeerService,
                          remoteBeerMapper: RemoteBeerMapper): BeerRemote {
        return RemoteBeerDataSource(beerService, remoteBeerMapper)
    }

    @Provides
    @PerApplication
    fun provideBeerService(retrofit: Retrofit): BeerService {
        return retrofit.create(BeerService::class.java)
    }

    @Provides
    @PerApplication
    fun provideRetrofit(moshiConverterFactory: MoshiConverterFactory,
                        okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(moshiConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @PerApplication
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @PerApplication
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @PerApplication
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }
}