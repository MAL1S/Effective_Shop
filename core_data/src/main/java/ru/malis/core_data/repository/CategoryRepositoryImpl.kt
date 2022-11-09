package ru.malis.core_data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import ru.malis.core_domain.repository.CategoryRepository
import ru.malis.core_database.dao.CategoryDao
import ru.malis.core_domain.models.Category
import ru.malis.core_util.coroutinedispatchers.IoDispatcher
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    @IoDispatcher private val IoDispatcher: CoroutineDispatcher
): CategoryRepository {

    override suspend fun getCategories(): List<Category> {
        return withContext(IoDispatcher) {
            categoryDao.getAllCategories()
        }
    }
}