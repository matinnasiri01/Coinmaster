package me.nasiri.coinmaster.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import me.nasiri.coinmaster.domain.model.FNews
import me.nasiri.coinmaster.domain.model.FCoinData


@Database(
    entities = [FCoinData::class, FNews::class], version = 1, exportSchema = false
)
abstract class CenterDatabase : RoomDatabase(){
    abstract val coinsDao: CoinsDao
    abstract val newsDao: NewsDao
}