package com.example.sample_crypto_tracker.CryptoData

data class CryptoApiResponse(
    val timestamp: Long,
    val `data`: List<CryptoEntity>,
)