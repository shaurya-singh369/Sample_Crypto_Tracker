package com.example.sample_crypto_tracker.CryptoData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_table")
data class CryptoEntity(
    @PrimaryKey(autoGenerate = true)
    val _id:Long,
    val changePercent24Hr: String,
    val id: String,
    val marketCapUsd: String,
    val maxSupply: String?,
    val name: String,
    val priceUsd: String,
    val rank: String,
    val supply: String,
    val symbol: String,
    val volumeUsd24Hr: String,
    val vwap24Hr: String?
)