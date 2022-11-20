package ru.malis.core_data.repository

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.malis.core_domain.models.Category
import ru.malis.core_domain.models.Product
import ru.malis.core_domain.models.ProductDetails
import ru.malis.core_domain.repository.ProductRepository
import ru.malis.core_network.api.ProductApi
import ru.malis.core_util.coroutinedispatchers.IoDispatcher
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ProductRepository {

    override suspend fun getProductByCategory(category: Category): Product {
        return withContext(ioDispatcher) {
            productApi.getProductByCategory(
                //category.requestName
            )
        }
    }

    override suspend fun getProductDetails(): ProductDetails {
        return withContext(ioDispatcher) {
            productApi.getProductDetails()
        }
    }
}