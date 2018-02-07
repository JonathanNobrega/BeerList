package com.jonathannobrega.beerlist.data.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class AuthorizationInterceptor @Inject constructor(
        @Named("breweryDbApiKey") private val breweryDbApiKey: String
) : Interceptor {

    private val AUTHORIZATION_KEY_NAME = "key"

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url()
                .newBuilder()
                .addQueryParameter(AUTHORIZATION_KEY_NAME, breweryDbApiKey)
                .build()

        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
