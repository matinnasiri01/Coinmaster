package me.nasiri.coinmaster.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.nasiri.coinmaster.domain.model.FNews
import me.nasiri.coinmaster.domain.util.Constants.TABLENEWS


@Dao
interface NewsDao {

    @Query("SELECT * FROM $TABLENEWS")
    suspend fun getNews(): List<FNews>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(list: List<FNews>)
}