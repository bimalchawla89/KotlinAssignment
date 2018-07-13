package com.suncorpassignment.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [(Transaction::class)], version = 1, exportSchema = false)
abstract class TransactionDatabase : RoomDatabase() {

    /**
     * Returns the Database Access Object to access transactions.
     * @return the Database Access Object for transactions
     */

    abstract fun transactionDao(): TransactionDao

}