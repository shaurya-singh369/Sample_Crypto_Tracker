package com.example.sample_crypto_tracker.schema

data class CryptoApiResponse(
    val timestamp: Long,
    val data: List<CryptoEntity>,
)