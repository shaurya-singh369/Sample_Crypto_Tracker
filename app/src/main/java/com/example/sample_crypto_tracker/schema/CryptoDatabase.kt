package com.example.sample_crypto_tracker.schema

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CryptoEntity::class], version = 1)
abstract class CryptoDatabase: RoomDatabase() {
    abstract fun cryptoDao(): CryptoDao
    companion object{
        private var INSTANCE: CryptoDatabase? = null
        fun getDatabase(context: Context):CryptoDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context, CryptoDatabase::class.java, "crypto_database").build()
                }

            }
            return INSTANCE!!
        }
    }
}