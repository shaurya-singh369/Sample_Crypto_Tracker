package com.example.sample_crypto_tracker.schema.database

import android.content.Context
import androidx.room.Room

object CryptoDatabaseHelper{
    private const val DATABASE_NAME = "crypto_database"
    fun getDatabase(context: Context): CryptoDatabase {
        return Room.databaseBuilder(
            context,
            CryptoDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

}