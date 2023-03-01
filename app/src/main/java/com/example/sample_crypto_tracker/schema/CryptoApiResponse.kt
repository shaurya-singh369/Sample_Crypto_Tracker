package com.example.sample_crypto_tracker.schema

data class CryptoApiResponse(
    val timestamp: Long,
    val data: List<CryptoEntity>,
)
data class SingleCryptoApiResponse(
    val timestamp: Long,
    val data: CryptoEntity,
)