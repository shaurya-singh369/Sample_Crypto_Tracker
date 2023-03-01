package com.example.sample_crypto_tracker

import android.app.Application
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import com.example.sample_crypto_tracker.api.CryptoDataApi
import com.example.sample_crypto_tracker.api.RetrofitHelper
import com.example.sample_crypto_tracker.repository.CryptoRepository
import com.example.sample_crypto_tracker.schema.database.CryptoDatabase
import com.example.sample_crypto_tracker.schema.database.CryptoDatabaseHelper
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RetrofitTest {

    private lateinit var cryptoApi: CryptoDataApi

    @Before
     fun setUp() {
        cryptoApi = RetrofitHelper.getInstance().create(CryptoDataApi::class.java)
    }
    @Test
    fun getCrypto_expectedHundredCrypto() = runBlocking{
        cryptoApi.getAssets().body()?.data.let { cryptoList ->
            if (cryptoList != null) {

                Assert.assertEquals(100, cryptoList.size)
            }
        }

    }
    @Test
    fun getCrypto_expectedNonEmptyList() = runBlocking {
        val cryptoList = cryptoApi.getAssets().body()?.data

        Assert.assertNotNull(cryptoList)
        Assert.assertTrue(cryptoList!!.isNotEmpty())
    }

    @Test
    fun getCrypto_expectedNotNull() = runBlocking {
        val response = cryptoApi.getAssets().body()

        Assert.assertNotNull(response)
        Assert.assertNotNull(response?.data)
    }

    @Test
    fun getCrypto_expectedBitcoin() = runBlocking {
        val bitcoin = cryptoApi.getAssetById("bitcoin").body()?.data
        Log.d("RetrofitTest", "bitcoin: $bitcoin")
        Assert.assertNotNull(bitcoin)
        Assert.assertEquals("bitcoin", bitcoin?.id)
    }

    @Test
    fun getCrypto_expected404Error() = runBlocking {
        val response = cryptoApi.getAssetById("nonexistentcrypto").code()
        Log.d("RetrofitTest", "response: $response")
        Assert.assertEquals(404, response)
    }

//check reponse codes
// wrong test cases
    //mock data create
        //verify the getcrypto method is called ,catch block

    @After
    fun tearDown() {

    }
}