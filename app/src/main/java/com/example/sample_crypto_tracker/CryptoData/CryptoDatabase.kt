package com.example.sample_crypto_tracker.CryptoData

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CryptoEntity::class], version = 1)
abstract class CryptoDatabase: RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao
}