package org.saham.fooddelivery.core.local_database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.saham.fooddelivery.features.common.domain.model.ui.OrderUiModel


@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(orderUiModel: List<OrderUiModel>)

    @Query("SELECT * FROM OrderUiModel")
    fun getAllAsFlow(): Flow<List<OrderUiModel>>

    @Query("DELETE FROM OrderUiModel")
    suspend fun clearAll()
}