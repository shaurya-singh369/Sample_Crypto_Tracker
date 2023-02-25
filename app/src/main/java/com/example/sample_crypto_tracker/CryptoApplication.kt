package com.example.sample_crypto_tracker

import android.app.Application
import com.example.sample_crypto_tracker.Api.CryptoDataApi
import com.example.sample_crypto_tracker.Api.RetrofitHelper
import com.example.sample_crypto_tracker.CryptoData.CryptoDatabase
import com.example.sample_crypto_tracker.repository.CryptoRepository

class CryptoApplication: Application() {
    lateinit var cryptoRepository: CryptoRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }
    private fun initialize() {
        val cryptoApi = RetrofitHelper.getInstance().create(CryptoDataApi::class.java)
        val dao= CryptoDatabase.getDatabase(applicationContext).cryptoDao()
        cryptoRepository= CryptoRepository(cryptoApi,dao,applicationContext)
    }
}