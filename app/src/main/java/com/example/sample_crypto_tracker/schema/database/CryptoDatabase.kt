package com.example.sample_crypto_tracker.schema.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sample_crypto_tracker.schema.CryptoDao
import com.example.sample_crypto_tracker.schema.CryptoEntity

@Database(entities = [CryptoEntity::class], version = 1)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao

}
