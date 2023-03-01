package com.example.sample_crypto_tracker.api


import com.example.sample_crypto_tracker.schema.CryptoApiResponse
import com.example.sample_crypto_tracker.schema.SingleCryptoApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CryptoDataApi {
    @GET("assets")
    suspend fun getAssets(): Response<CryptoApiResponse>
    @GET("assets/{id}")
    suspend fun getAssetById(@Path("id") id:String): Response<SingleCryptoApiResponse>


}