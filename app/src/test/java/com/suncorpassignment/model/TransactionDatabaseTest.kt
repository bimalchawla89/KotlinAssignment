//package com.suncorpassignment.model
//
//import android.support.test.InstrumentationRegistry
//import android.support.test.runner.AndroidJUnit4
//import org.junit.After
//import org.junit.Assert
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.robolectric.RobolectricTestRunner
//
//@RunWith(RobolectricTestRunner::class)
//class TransactionDatabaseTest {
//
//    private var transactionDao: TransactionDao? = null
//
//    @Before
//    fun setup() {
//        TransactionDatabase.TEST_MODE = true
//        transactionDao = TransactionDatabase.getInstance(InstrumentationRegistry.getContext())
//    }
//
//    @After
//    fun tearDown() {
//
//    }
//
//    @Test
//    fun should_Insert_Transaction_Item() {
//        val transaction = Transaction(1, "Banana", 123.2, "7864")
//        transactionDao?.insert(transaction)
//        val transactionTest = transactionDao?.getAll()!!.get(0)
//        Assert.assertEquals(transaction.description, transactionTest.description)
//    }
//
////    // Copied from stackoverflow
////    @Throws(InterruptedException::class)
////    fun <T> getValue(liveData: List<Transaction>): T {
////        val data = arrayOfNulls<Any>(1)
////        val latch = CountDownLatch(1)
////        val observer = object : Observer<T> {
////            override fun onChanged(t: T?) {
////                data[0] = t
////                latch.countDown()
////                liveData.removeObserver(this)//To change body of created functions use File | Settings | File Templates.
////            }
////
////        }
////        liveData.observeForever(observer)
////        latch.await(2, TimeUnit.SECONDS)
////
////        return data[0] as T
////    }
//}