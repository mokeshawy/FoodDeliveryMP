package org.saham.fooddelivery.core.local_database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import org.saham.fooddelivery.features.common.domain.model.ui.OrderUiModel


@Database(entities = [OrderUiModel::class], version = 1)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): OrderDao
}

