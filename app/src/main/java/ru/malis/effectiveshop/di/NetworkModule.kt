package ru.malis.effectiveshop.di

import dagger.Module
import dagger.Provides
import ru.malis.core_network.api.ProductApi
import ru.malis.core_network.clients.ProductClient

@Module
interface NetworkModule {

    companion object {

        @AppScope
        @Provides
        fun provideProductApi(): ProductApi {
            return ProductClient.buildProductApi()
        }
    }
}