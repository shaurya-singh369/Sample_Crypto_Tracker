package com.example.sample_crypto_tracker

import android.app.Application
import com.example.sample_crypto_tracker.api.CryptoDataApi
import com.example.sample_crypto_tracker.api.RetrofitHelper
import com.example.sample_crypto_tracker.repository.CryptoRepository
import com.example.sample_crypto_tracker.schema.database.CryptoDatabaseHelper

class CryptoApplication: Application() {
    lateinit var cryptoRepository: CryptoRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }
    private fun initialize() {
        val cryptoApi = RetrofitHelper.getInstance().create(CryptoDataApi::class.java)
        val dao= CryptoDatabaseHelper.getDatabase(applicationContext).cryptoDao()
        cryptoRepository= CryptoRepository(cryptoApi,dao,applicationContext)
    }
}