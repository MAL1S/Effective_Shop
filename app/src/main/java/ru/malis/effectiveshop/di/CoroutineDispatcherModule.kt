package ru.malis.effectiveshop.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import ru.malis.core_util.coroutinedispatchers.IoDispatcher

@Module
interface CoroutineDispatcherModule {

    companion object {

        @AppScope
        @Provides
        @IoDispatcher fun provideCoroutineDispatcher(): CoroutineDispatcher {
            return Dispatchers.IO
        }
    }
}