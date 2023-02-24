package com.example.sample_crypto_tracker.Api


import com.example.sample_crypto_tracker.CryptoData.CryptoApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface CryptoDataApi {
    @GET("assets")
    suspend fun getAssets(): Response<CryptoApiResponse>

}