package com.example.sample_crypto_tracker.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_crypto_tracker.schema.CryptoEntity
import com.example.sample_crypto_tracker.repository.CryptoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val cryptoRepository: CryptoRepository) : ViewModel() {


    fun getAll(): LiveData<List<CryptoEntity>> {
        return cryptoRepository.getAll()
    }

    fun insertAll(cryptoData: List<CryptoEntity>) {
        viewModelScope.launch(Dispatchers.IO) {
            cryptoRepository.insertAll(cryptoData)
        }

    }

}