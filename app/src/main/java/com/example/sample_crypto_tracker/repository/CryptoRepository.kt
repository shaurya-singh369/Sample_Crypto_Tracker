package com.example.sample_crypto_tracker.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sample_crypto_tracker.api.CryptoDataApi
import com.example.sample_crypto_tracker.schema.CryptoDao
import com.example.sample_crypto_tracker.schema.CryptoEntity
import com.example.sample_crypto_tracker.NetworkUtils

class CryptoRepository(
    private val cryptoService: CryptoDataApi,
    private val cryptoDao: CryptoDao,
    private val applicationContext: Context
) {
    private val cryptoLiveData = MutableLiveData<List<CryptoEntity>>()
    val cryptoData: LiveData<List<CryptoEntity>>
        get() = cryptoLiveData

    suspend fun getCryptoData(): List<CryptoEntity> {
        try {
            if (NetworkUtils.isInternetAvailable(applicationContext)) {
                val response = cryptoService.getAssets()
                if (response.body() != null) {
                   response.body()?.let {
                       insertAll(it.data)
                       cryptoLiveData.postValue(it.data)

                   }
                    return response.body()!!.data
                }
                return emptyList()
            } else {
                return getAll()
            }
        } catch (e: Exception) {
            Log.d("getCryptoData", "Error: ${e.message}")
            return getAll()
        }
    }

    private suspend fun insertAll(cryptoData: List<CryptoEntity>) {
        try {
            cryptoDao.insertAll(cryptoData)
        } catch (e: Exception) {
            Log.d("insertAll", "Error: ${e.message}")
        }
    }
    private fun getAll(): List<CryptoEntity> {
        return try {
            cryptoDao.getAll()
        } catch (e: Exception) {
            Log.d("getAll", "Error: ${e.message}")
            emptyList()
        }

    }
}
