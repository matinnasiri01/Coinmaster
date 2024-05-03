package me.nasiri.coinmaster.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.nasiri.coinmaster.domain.model.FCoinData
import me.nasiri.coinmaster.util.Constans.TableCoin


@Dao
interface CoinsDao {

    @Query("SELECT * FROM $TableCoin")
    suspend fun getCoins(): List<FCoinData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoins(list: List<FCoinData>)
}