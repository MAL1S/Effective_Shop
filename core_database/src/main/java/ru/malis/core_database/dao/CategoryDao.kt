package ru.malis.core_database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.malis.core_domain.models.Category

@Dao
interface CategoryDao {

    @Insert
    suspend fun insertAllCategories(categories: List<Category>)

    @Query("SELECT * FROM Category")
    fun getAllCategories(): List<Category>
}