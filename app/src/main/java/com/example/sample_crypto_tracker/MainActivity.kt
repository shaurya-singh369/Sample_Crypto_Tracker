package com.example.sample_crypto_tracker

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sample_crypto_tracker.schema.*
import com.example.sample_crypto_tracker.viewModels.MainViewModel
import com.example.sample_crypto_tracker.viewModels.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val repository=(application as CryptoApplication).cryptoRepository


        mainViewModel=ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

                GlobalScope.launch(Dispatchers.IO) {
                    val response = repository.getCryptoData()
                    if (response != null) {
                        if (response != null) {
                            val currencyList = response.map {
                                CryptoEntity(
                                    _id = 0,
                                    changePercent24Hr = it.changePercent24Hr,
                                    id = it.id,
                                    marketCapUsd = it.marketCapUsd,
                                    maxSupply = it.maxSupply,
                                    name = it.name,
                                    priceUsd = it.priceUsd,
                                    rank = it.rank,
                                    supply = it.supply,
                                    symbol = it.symbol,
                                    volumeUsd24Hr = it.volumeUsd24Hr,
                                    vwap24Hr = it.vwap24Hr
                                )
                            }
                            val adapter = Adapter(currencyList)
                            runOnUiThread {
                                currency_list.adapter = adapter
                                currency_list.layoutManager =
                                    LinearLayoutManager(this@MainActivity)
                            }

                        }

                    }
                }


        }
    //onresume
    override fun onResume() {
        super.onResume()
        val repository=(application as CryptoApplication).cryptoRepository
        mainViewModel=ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val response = repository.getCryptoData()
            if (response != null) {
                if (response != null) {
                    val currencyList = response.map {
                        CryptoEntity(
                            _id = 0,
                            changePercent24Hr = it.changePercent24Hr,
                            id = it.id,
                            marketCapUsd = it.marketCapUsd,
                            maxSupply = it.maxSupply,
                            name = it.name,
                            priceUsd = it.priceUsd,
                            rank = it.rank,
                            supply = it.supply,
                            symbol = it.symbol,
                            volumeUsd24Hr = it.volumeUsd24Hr,
                            vwap24Hr = it.vwap24Hr
                        )
                    }
                    val adapter = Adapter(currencyList)
                    runOnUiThread {
                        currency_list.adapter = adapter
                        currency_list.layoutManager =
                            LinearLayoutManager(this@MainActivity)
                    }
                }

            }
        }

    }

    }



