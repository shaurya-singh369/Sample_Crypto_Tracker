package com.example.sample_crypto_tracker

import android.util.Log
import com.example.sample_crypto_tracker.api.CryptoDataApi
import com.example.sample_crypto_tracker.api.RetrofitHelper
import com.example.sample_crypto_tracker.repository.CryptoRepository
import com.example.sample_crypto_tracker.schema.CryptoDao
import com.example.sample_crypto_tracker.schema.CryptoEntity
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockedConstruction
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

//class RetrofitTest {
//
//    @Mock
//    private lateinit var cryptoRepository: CryptoRepository
//
//    @Before
//    fun setUp() {
//        MockitoAnnotations.openMocks(this)
//    }
//    @Test
//    fun getCrypto_expectedEmptyList() = runBlocking {
//        Mockito.`when`(cryptoRepository.getCryptoData()).thenReturn(emptyList())
//        val cryptoList = cryptoRepository.getCryptoData()
//        Assert.assertTrue(cryptoList.isEmpty())
//    }
//
////check reponse codes
//// wrong test cases
//    //mock data create
//    //verify the getcrypto method is called ,catch block
//
//    @After
//    fun tearDown() {
//
//    }
//}
class CryptoRepositoryTest {
    @Mock
    private lateinit var cryptoRepository: CryptoRepository
    @Mock
    private lateinit var cryptoService: CryptoDataApi
    @Mock
    private lateinit var cryptoDao: CryptoDao

    private lateinit var cryptoEntity: CryptoEntity
    private lateinit var cryptoEntity2: CryptoEntity

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        cryptoEntity = CryptoEntity(
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
        cryptoEntity2 = CryptoEntity(
            id = "ethereum",
            rank = "2",
            symbol = "ETH",
            name = "Ethereum",
            supply = "102000000.0000000000000000",
            maxSupply = null,
            marketCapUsd = "21300000000.0000000000000000",
            volumeUsd24Hr = "2927959461.1750323310959460",
            priceUsd = "6929.8217756835584756",
            changePercent24Hr = "-0.8101417214350335",
            vwap24Hr = "7175.0663247679233209"
        )
    }

    @Test
    fun `test getCryptoData when internet is available and data is returned`() = runBlocking {
        Mockito.`when`(cryptoRepository.getCryptoData()).thenReturn(mockCryptoList())
        val cryptoData = cryptoRepository.getCryptoData()
        Assert.assertEquals(2, cryptoData.size)
    }

    @Test
    fun `test getCryptoData when internet is available but data is not returned`() = runBlocking {
        Mockito.`when`(cryptoRepository.getCryptoData()).thenReturn(emptyList())
        val cryptoData = cryptoRepository.getCryptoData()
        Assert.assertTrue(cryptoData.isEmpty())
    }
    @Test
    fun `test getCryptoData when internet is available but fetching data throws an exception`() = runBlocking {
        Mockito.`when`(cryptoRepository.getCryptoData()).thenThrow(RuntimeException())
        try {
            cryptoRepository.getCryptoData()
            Assert.fail("Expected exception not thrown")
        } catch (ex: Exception) {
            // Expected behavior
        }
    }
    @Test
    fun `test getCryptoDataFromDb fetches data from database`() = runBlocking {
        Mockito.`when`(cryptoDao.getAll()).thenReturn(mockCryptoList())
        Assert.assertEquals(cryptoEntity, cryptoDao.getAll()[0])
    }
    private fun mockCryptoList(): List<CryptoEntity> {
        return listOf(cryptoEntity, cryptoEntity2)
    }
}