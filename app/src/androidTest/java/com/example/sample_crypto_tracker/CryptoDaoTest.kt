package com.example.sample_crypto_tracker

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.sample_crypto_tracker.schema.CryptoDao

import com.example.sample_crypto_tracker.schema.database.CryptoDatabase
import com.example.sample_crypto_tracker.schema.CryptoEntity
import kotlinx.coroutines.runBlocking
import org.junit.*

class CryptoDaoTest {
//    @get:Rule
//    val instantExecutorRule =InstantTaskExecutorRule()

    lateinit var cryptoDao: CryptoDao
    lateinit var cryptoDatabase: CryptoDatabase

    @Before
    fun setUp() {
        cryptoDatabase=Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), CryptoDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        cryptoDao=cryptoDatabase.cryptoDao()
    }

    @Test
    fun insertCrypto_expectedSingleCrypto() = runBlocking{
        val cryptoEntity = CryptoEntity(
            id = "bitcoin",
        rank = "1",
        symbol ="BTC",
        name ="Bitcoin",
        supply = "17193925.0000000000000000",
        maxSupply ="21000000.0000000000000000",
        marketCapUsd = "119150835874.4699281625807300",
        volumeUsd24Hr = "2927959461.1750323310959460",
        priceUsd = "6929.8217756835584756",
        changePercent24Hr = "-0.8101417214350335",
        vwap24Hr = "7175.0663247679233209"
        )
        cryptoDao.insertAll(listOf(cryptoEntity))
        val cryptoList = cryptoDao.getAll()
        Log.d("CryptoDaoTest", "cryptoList: $cryptoList")

            Assert.assertEquals(1, cryptoList.size)
            Assert.assertEquals("bitcoin", cryptoList[0].id)


    }


    @After
    fun tearDown() {
        cryptoDatabase.close()
    }
}