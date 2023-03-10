package com.example.sample_crypto_tracker.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sample_crypto_tracker.schema.CryptoEntity
import com.example.sample_crypto_tracker.repository.CryptoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val cryptoRepository: CryptoRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            cryptoRepository.getCryptoData()
        }
    }
    val cryptoData: LiveData<List<CryptoEntity>>
        get() = cryptoRepository.cryptoData

    fun refreshData() {
        viewModelScope.launch(Dispatchers.IO) {
            cryptoRepository.getCryptoData()
        }
    }

}