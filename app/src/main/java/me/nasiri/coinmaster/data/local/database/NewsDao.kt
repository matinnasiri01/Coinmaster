package me.nasiri.coinmaster.data.local.database

interface NewsDao {


    /* todo get All news from local database*/
    suspend fun getAllNews()

    suspend fun insertAllNews()
}