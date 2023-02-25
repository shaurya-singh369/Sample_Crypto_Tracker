package com.example.sample_crypto_tracker.CryptoData

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CryptoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cryptoData: List<CryptoEntity>)
    @Update
    suspend fun updateAll(cryptoData: CryptoEntity)
    @Query("SELECT * FROM crypto_table")
    fun getAll(): LiveData<List<CryptoEntity>>

}