//package com.suncorpassignment.ui.transaction
//
//import android.accounts.NetworkErrorException
//import android.support.test.InstrumentationRegistry
//import android.support.test.runner.AndroidJUnit4
//import com.suncorpassignment.model.Transaction
//import com.suncorpassignment.model.TransactionDao
//import com.suncorpassignment.model.TransactionDatabase
//import io.reactivex.Observable
//import io.reactivex.android.plugins.RxAndroidPlugins
//import io.reactivex.schedulers.Schedulers
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.Mockito.*
//import org.mockito.MockitoAnnotations
//
//@RunWith(AndroidJUnit4::class)
//class TransactionPresenterTest {
//
//    @Mock
//    lateinit var mockView: TransactionView
//
//    @Mock
//    lateinit var transactionDao: TransactionDao
//
//    lateinit var database: TransactionDatabase
//
//    lateinit var transactionPresenter: TransactionPresenter
//
//
////    @Before
////    fun setUp() {
//////        val context = InstrumentationRegistry.getTargetContext()
////        MockitoAnnotations.initMocks(this)
////        transactionPresenter = TransactionPresenter(mockView)
////        TransactionDatabase.TEST_MODE = true
////        transactionDao = TransactionDatabase.getInstance(InstrumentationRegistry.getTargetContext())
//////        database = Room.inMemoryDatabaseBuilder(context, TransactionDatabase::class.java).build()
//////        transactionDao = database.transactionDao()
////    }
//
//
//    @Before @Throws fun setUp(){
//        RxAndroidPlugins.setInitMainThreadSchedulerHandler({ Schedulers.trampoline()})
//        MockitoAnnotations.initMocks(this)
//
//        transactionPresenter = TransactionPresenter(transactionView = mockView)
//    }
//
//    @Test fun testBlogsReturnsList() {
//        val transactions = getMockedTransactions(5)
//        `when`(transactionDao.getAll()).thenReturn(transactions)
//
//        transactionPresenter.loadTransactions()
//
//        // Verify view method is called
//        verify(mockView).updateTransactions(transactions)
//    }
//
//    @Test fun testBlogsReturnsError() {
//        `when`(transactionDao.getAll()).thenReturn(listOf())
//
//        transactionPresenter.loadTransactions()
//
//        // View is never called
//        verify(mockView, never()).updateTransactions(any())
//    }
//
//    fun getMockedTransactions(count : Int) : List<Transaction> {
//        val blogs = ArrayList<Transaction>()
//        for (i in 0..count) {
//            val blog = mock(Transaction::class.java)
//            blogs.add(blog)
//        }
//        return blogs
//    }
//
//
////    @Test
////    fun testLoadTransactions_error() {
////        // Given
////        val error = "Test error"
//////        val single: List<Transaction> = create { emitter ->
//////            emitter.onError(Exception(error))
//////        }
////
////        // When
////        `when`(transactionDao.getAll()).thenReturn(listOf())
////
////        transactionPresenter.loadTransactions()
////
////        // Then
////        verify(mockView).showLoading()
////        verify(mockView).hideLoading()
////        verify(mockView).showError(error)
////    }
////
////    @Test
////    fun testGetDetails_success() {
////        // Given
////        val details: List<Transaction> = listOf()
//////        val single: List<Transaction> = Single.create { emitter ->
//////            emitter.onSuccess(details)
//////        }
////
////        // When
////        `when`(transactionDao.getAll()).thenReturn(details)
////
////        transactionPresenter.loadTransactions()
////
////        // Then
////        verify(mockView).showLoading()
////        verify(mockView).hideLoading()
////        verify(mockView).updateTransactions(details)
////    }
//}
