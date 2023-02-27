package com.example.sample_crypto_tracker.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.sample_crypto_tracker.api.CryptoDataApi
import com.example.sample_crypto_tracker.schema.CryptoDao
import com.example.sample_crypto_tracker.schema.CryptoEntity
import com.example.sample_crypto_tracker.NetworkUtils

class CryptoRepository(
    private val cryptoService: CryptoDataApi,
    private val cryptoDao: CryptoDao,
    private val applicationContext: Context
) {

    suspend fun getCryptoData(): List<CryptoEntity> {
       if(NetworkUtils.isInternetAvailable(applicationContext)){
           val response = cryptoService.getAssets()
           if(response?.body()!=null){
               insertAll(response.body()!!.data)
               return response.body()!!.data
           }
           return emptyList()
       }
        else
              return getAll()!!
    }
    suspend fun insertAll(cryptoData: List<CryptoEntity>) {
        cryptoDao.insertAll(cryptoData)
    }
    suspend fun updateAll(cryptoData: CryptoEntity) {
        cryptoDao.updateAll(cryptoData)
    }
    fun getAll(): List<CryptoEntity> {
        return cryptoDao.getAll()
    }
}
