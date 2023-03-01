package com.example.sample_crypto_tracker.schema

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "crypto_table")
data class CryptoEntity(
    @PrimaryKey
    val id: String,
    val changePercent24Hr: String,
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