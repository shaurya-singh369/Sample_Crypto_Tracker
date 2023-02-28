package com.example.sample_crypto_tracker.repository

import android.content.Context
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
        if (NetworkUtils.isInternetAvailable(applicationContext)) {
            val response = cryptoService.getAssets()
            if (response?.body() != null) {
                insertAll(response.body()!!.data)
                cryptoLiveData.postValue(response.body()!!.data)
                return response.body()!!.data
            }
            return emptyList()
        } else
            return getAll().value!!
    }

    suspend fun insertAll(cryptoData: List<CryptoEntity>) {
        cryptoDao.insertAll(cryptoData)
    }

    suspend fun updateAll(cryptoData: List<CryptoEntity>) {
        cryptoDao.updateAll(cryptoData)
    }

    fun getAll(): LiveData<List<CryptoEntity>> {
        return cryptoDao.getAll()
    }
}
