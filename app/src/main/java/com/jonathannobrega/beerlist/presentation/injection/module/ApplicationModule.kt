package com.jonathannobrega.beerlist.presentation.injection.module

import android.app.Application
import android.content.Context
import com.jonathannobrega.beerlist.data.executor.JobExecutor
import com.jonathannobrega.beerlist.data.network.interceptor.AuthorizationInterceptor
import com.jonathannobrega.beerlist.domain.executor.PostExecutionThread
import com.jonathannobrega.beerlist.domain.executor.ThreadExecutor
import com.jonathannobrega.beerlist.presentation.UiThread
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    private val API_BASE_URL = "http://api.brewerydb.com/v2/"

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }

    @Provides
    @Named("breweryDbApiKey")
    fun provideApiAuthenticationToken(): String {
        return "INSERT_BREWERY_DB_API_KEY"
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authorizationInterceptor: AuthorizationInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(authorizationInterceptor)
                .build()
    }

    @Provides
    @Singleton
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
    @Singleton
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }
}