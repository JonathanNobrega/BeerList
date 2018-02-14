package com.jonathannobrega.beerlist.presentation.injection.module

import android.app.Application
import android.content.Context
import com.jonathannobrega.beerlist.data.executor.JobExecutor
import com.jonathannobrega.beerlist.domain.executor.PostExecutionThread
import com.jonathannobrega.beerlist.domain.executor.ThreadExecutor
import com.jonathannobrega.beerlist.presentation.UiThread
import com.jonathannobrega.beerlist.presentation.injection.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }
}