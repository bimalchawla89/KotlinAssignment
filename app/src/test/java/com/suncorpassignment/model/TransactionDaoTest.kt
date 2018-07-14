package com.suncorpassignment.model

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import junit.framework.TestCase.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TransactionDaoTest {

    lateinit var transactionDao: TransactionDao
    lateinit var database: TransactionDatabase

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, TransactionDatabase::class.java).build()
        transactionDao = database.transactionDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertedAndRetrievedUsersMatch() {
        val transactions = listOf(Transaction(1, "Restaurent payment", 100.95, "2018-07-14T10:23:12.123Z"))
        transactionDao.insert(*transactions.toTypedArray())

        val allTransactions = transactionDao.getAll()
        assertEquals(transactions, allTransactions)
    }

    @Test
    fun testUsersOrderedByCorrectly() {
        val transactions = listOf(
                Transaction(1, "First Restaurant", 100.10, "2018-07-10T10:03:27.123Z"),
                Transaction(2, "Second Restaurant", 500.20, "2018-07-12T20:30:56.290Z"),
                Transaction(3, "Third Restaurant", 300.30, "2018-07-14T01:15:22.563Z"))
        transactionDao.insert(*transactions.toTypedArray())

        val allTransactions = transactionDao.getAll()
        val expectedTransactions = transactions.sortedByDescending { it.effectiveDate }
        assertEquals(expectedTransactions, allTransactions)
    }

    @Test
    fun testConflictingInsertsReplaceUsers() {
        val transactions = listOf(
                Transaction(1, "First Restaurant", 100.10, "2018-07-10T10:03:27.123Z"),
                Transaction(2, "Second Restaurant", 500.20, "2018-07-12T20:30:56.290Z"),
                Transaction(3, "Third Restaurant", 300.30, "2018-07-14T01:15:22.563Z"))


        val transactions2 = listOf(
                Transaction(1, "fourth Restaurant", 200.10, "2018-07-04T10:03:27.123Z"),
                Transaction(2, "fifth Restaurant", 800.20, "2018-07-02T20:30:56.290Z"),
                Transaction(4, "six Restaurant", 110.34, "2018-07-12T01:15:22.563Z"))

        transactionDao.insert(*transactions.toTypedArray())
        transactionDao.insert(*transactions2.toTypedArray())

        val allTransactions = transactionDao.getAll()
        val expectedTransactions = listOf(
                Transaction(4, "six Restaurant", 110.34, "2018-07-12T01:15:22.563Z"),
                Transaction(1, "First Restaurant", 100.10, "2018-07-10T10:03:27.123Z"),
                Transaction(2, "Second Restaurant", 500.20, "2018-07-12T20:30:56.290Z"),
                Transaction(3, "Third Restaurant", 300.30, "2018-07-14T01:15:22.563Z"))

        assertEquals(expectedTransactions, allTransactions)
    }

}