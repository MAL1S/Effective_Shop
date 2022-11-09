package ru.malis.core_database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.malis.core_database.dao.CategoryDao
import ru.malis.core_domain.models.Category

@Database(entities = [
    Category::class
], version = 1)
abstract class CategoryDB: RoomDatabase() {

    abstract fun getCategoryDao(): CategoryDao
}