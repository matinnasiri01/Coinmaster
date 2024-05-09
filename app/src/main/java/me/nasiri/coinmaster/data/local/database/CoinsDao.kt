package me.nasiri.coinmaster.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import me.nasiri.coinmaster.domain.model.FCoinData
import me.nasiri.coinmaster.domain.util.Constants.TABLECOIN


@Dao
interface CoinsDao {

    @Query("SELECT * FROM $TABLECOIN")
    fun getCoins(): Flow<List<FCoinData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(list: List<FCoinData>)
}