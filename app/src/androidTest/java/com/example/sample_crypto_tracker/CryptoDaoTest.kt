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
    @Test
    fun insertCrypto_expectedMultipleCrypto() = runBlocking{
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
        cryptoDao.insertAll(listOf(cryptoEntity,cryptoEntity,cryptoEntity))
        val cryptoList = cryptoDao.getAll()
        Log.d("CryptoDaoTest", "cryptoList: $cryptoList")

        Assert.assertEquals(3, cryptoList.size)


    }
//write more test cases

    @Test
    fun insertAndDeleteCrypto_expectedEmptyList() = runBlocking {
        val cryptoEntity = CryptoEntity(
            id = "bitcoin",
            rank = "1",
            symbol = "BTC",
            name = "Bitcoin",
            supply = "17193925.0000000000000000",
            maxSupply = "21000000.0000000000000000",
            marketCapUsd = "119150835874.4699281625807300",
            volumeUsd24Hr = "2927959461.1750323310959460",
            priceUsd = "6929.8217756835584756",
            changePercent24Hr = "-0.8101417214350335",
            vwap24Hr = "7175.0663247679233209"
        )
        cryptoDao.insertAll(listOf(cryptoEntity))
        val cryptoListBeforeDelete = cryptoDao.getAll()
        Assert.assertEquals(1, cryptoListBeforeDelete.size)
        cryptoDao.deleteAll(listOf(cryptoEntity))
        val cryptoListAfterDelete = cryptoDao.getAll()
        Assert.assertEquals(0, cryptoListAfterDelete.size)
    }

    @Test
    fun insertAndUpdateCrypto_expectedUpdatedCrypto() = runBlocking {
        val cryptoEntity = CryptoEntity(
            id = "bitcoin",
            rank = "1",
            symbol = "BTC",
            name = "Bitcoin",
            supply = "17193925.0000000000000000",
            maxSupply = "21000000.0000000000000000",
            marketCapUsd = "119150835874.4699281625807300",
            volumeUsd24Hr = "2927959461.1750323310959460",
            priceUsd = "6929.8217756835584756",
            changePercent24Hr = "-0.8101417214350335",
            vwap24Hr = "7175.0663247679233209"
        )
        cryptoDao.insertAll(listOf(cryptoEntity))
        val cryptoListBeforeUpdate = cryptoDao.getAll()
        Assert.assertEquals(1, cryptoListBeforeUpdate.size)
        val updatedCryptoEntity = cryptoEntity.copy(priceUsd = "7000.00")
        cryptoDao.updateAll(listOf(updatedCryptoEntity))
        val cryptoListAfterUpdate = cryptoDao.getAll()
        Assert.assertEquals(1, cryptoListAfterUpdate.size)
        Assert.assertEquals(updatedCryptoEntity.priceUsd, cryptoListAfterUpdate[0].priceUsd)
    }

    @After
    fun tearDown() {
        cryptoDatabase.close()
    }
}