package com.example.sample_crypto_tracker.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sample_crypto_tracker.repository.CryptoRepository

class MainViewModelFactory(private val cryptoRepository: CryptoRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(cryptoRepository) as T
    }
}