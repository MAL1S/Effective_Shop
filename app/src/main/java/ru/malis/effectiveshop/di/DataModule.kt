package ru.malis.effectiveshop.di

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import ru.malis.core_domain.repository.CategoryRepository
import ru.malis.core_data.repository.CategoryRepositoryImpl
import ru.malis.core_data.repository.ProductRepositoryImpl
import ru.malis.core_database.dao.CategoryDao
import ru.malis.core_domain.repository.ProductRepository
import ru.malis.core_network.api.ProductApi
import ru.malis.core_util.coroutinedispatchers.IoDispatcher

@Module
interface DataModule {

    companion object {

        @AppScope
        @Provides
        fun provideCategoryRepository(
            categoryDao: CategoryDao,
            @IoDispatcher IODispatcher: CoroutineDispatcher
        ): CategoryRepository {
            return CategoryRepositoryImpl(categoryDao, IODispatcher)
        }

        @AppScope
        @Provides
        fun provideProductRepository(
            productApi: ProductApi,
            @IoDispatcher IODispatcher: CoroutineDispatcher
        ): ProductRepository {
            return ProductRepositoryImpl(productApi, IODispatcher)
        }
    }
}