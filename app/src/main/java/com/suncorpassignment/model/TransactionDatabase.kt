package com.suncorpassignment.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.suncorpassignment.utils.DATABASE_NAME

/**
 * It provides Database instance by extending the Room database and Dao method to override further
 */
@Database(entities = [(Transaction::class)], version = 1, exportSchema = false)
abstract class TransactionDatabase : RoomDatabase() {

    /**
     * Returns the Database Access Object to access transactions.
     * @return the Database Access Object for transactions
     */

    abstract fun transactionDao(): TransactionDao

    companion object {
        var TEST_MODE = false

        private var db: TransactionDatabase? = null
        private var dbInstance: TransactionDao? = null

        fun getInstance(context: Context): TransactionDao {
            if (dbInstance == null) {
                if (TEST_MODE) {
                    db = Room.inMemoryDatabaseBuilder(context, TransactionDatabase::class.java).allowMainThreadQueries().build()
                    dbInstance = db?.transactionDao()
                } else {
                    db = Room.databaseBuilder(context, TransactionDatabase::class.java, DATABASE_NAME).build()
                    dbInstance = db?.transactionDao()
                }
            }
            return dbInstance!!;
        }

        private fun close() {
            db?.close()
        }
    }

}