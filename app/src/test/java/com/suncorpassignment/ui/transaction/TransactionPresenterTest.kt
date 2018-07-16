package com.suncorpassignment.ui.transaction

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.suncorpassignment.model.Transaction
import com.suncorpassignment.model.TransactionDao
import com.suncorpassignment.model.TransactionDatabase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class TransactionPresenterTest {

    @Mock
    lateinit var mockView: TransactionView

    @Mock
    lateinit var transactionDao: TransactionDao

    lateinit var database: TransactionDatabase

    lateinit var transactionPresenter: TransactionPresenter


    @Before
    fun setUp() {
//        val context = InstrumentationRegistry.getTargetContext()
        MockitoAnnotations.initMocks(this)
        transactionPresenter = TransactionPresenter(mockView)
        TransactionDatabase.TEST_MODE = true
        transactionDao = TransactionDatabase.getInstance(InstrumentationRegistry.getTargetContext())
//        database = Room.inMemoryDatabaseBuilder(context, TransactionDatabase::class.java).build()
//        transactionDao = database.transactionDao()
    }

    @Test
    fun testLoadTransactions_error() {
        // Given
        val error = "Test error"
//        val single: List<Transaction> = create { emitter ->
//            emitter.onError(Exception(error))
//        }

        // When
        `when`(transactionDao.getAll()).thenReturn(listOf())

        transactionPresenter.loadTransactions()

        // Then
        verify(mockView).showLoading()
        verify(mockView).hideLoading()
        verify(mockView).showError(error)
    }

    @Test
    fun testGetDetails_success() {
        // Given
        val details: List<Transaction> = listOf()
//        val single: List<Transaction> = Single.create { emitter ->
//            emitter.onSuccess(details)
//        }

        // When
        `when`(transactionDao.getAll()).thenReturn(details)

        transactionPresenter.loadTransactions()

        // Then
        verify(mockView).showLoading()
        verify(mockView).hideLoading()
        verify(mockView).updateTransactions(details)
    }
}
