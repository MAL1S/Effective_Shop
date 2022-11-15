package ru.malis.effectiveshop.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.malis.core_database.dao.CategoryDao
import ru.malis.core_database.db.CategoryDB
import ru.malis.effectiveshop.App

@Module
interface DatabaseModule {

    companion object {

        @AppScope
        @Provides
        fun provideCategoryDB(): CategoryDB {
            return Room.databaseBuilder(
                App.INSTANCE.applicationContext,
                CategoryDB::class.java,
                "category_database"
            ).build()
        }

        @AppScope
        @Provides
        fun provideCategoryDao(categoryDB: CategoryDB): CategoryDao {
            return categoryDB.getCategoryDao()
        }
    }
}