package com.example.sample_crypto_tracker.cryptoData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_table")
data class CryptoEntity(
    @PrimaryKey
    val timestamp: Long,
    val `data`: List<Data>,

)