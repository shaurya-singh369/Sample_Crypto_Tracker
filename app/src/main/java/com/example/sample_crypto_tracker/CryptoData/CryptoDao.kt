package com.example.sample_crypto_tracker.CryptoData

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CryptoDao {
    @Insert
    suspend fun insertAll(cryptoData: List<CryptoEntity>)
    @Update
    suspend fun updateAll(cryptoData: CryptoEntity)
    @Query("SELECT * FROM crypto_table")
    fun getAll(): LiveData<List<CryptoEntity>>

}