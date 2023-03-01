package com.example.sample_crypto_tracker

import com.example.sample_crypto_tracker.api.CryptoDataApi
import com.example.sample_crypto_tracker.api.RetrofitHelper
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RetrofitTest {


    lateinit var cryptoApi: CryptoDataApi

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
//check reponse codes
// wrong test cases
    //mock data create
        //verify the getcrypto method is called ,catch block

    @After
    fun tearDown() {

    }
}