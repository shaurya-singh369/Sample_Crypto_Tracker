package com.example.sample_crypto_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.sample_crypto_tracker.Api.CryptoDataApi
import com.example.sample_crypto_tracker.Api.RetrofitHelper
import com.example.sample_crypto_tracker.CryptoData.CryptoDatabase
import com.example.sample_crypto_tracker.CryptoData.CryptoApiResponse
import com.example.sample_crypto_tracker.CryptoData.CryptoEntity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var database: CryptoDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database= Room.databaseBuilder(applicationContext, CryptoDatabase::class.java,"crypto_database").build()

        val cryptoApi = RetrofitHelper.getInstance().create(CryptoDataApi::class.java)
        GlobalScope.launch {
            val response = cryptoApi.getAssets()
            if (response != null) {
                Log.d("MainActivity", response.body().toString())
                val cryptoData:CryptoApiResponse? = response.body()
                if (cryptoData != null) {
                    cryptoData.data.forEach {
                        Log.d("MainActivity", it.name)
                    }
                    val currencyList = cryptoData.data.map {
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
                        currency_list.layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                    database.cryptoDao().insertAll(currencyList)
                }

            }
        }
    }
}
