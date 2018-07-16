package com.suncorpassignment.injections.module

import android.arch.persistence.room.Room
import android.content.Context
import com.suncorpassignment.model.TransactionDao
import com.suncorpassignment.model.TransactionDatabase
import com.suncorpassignment.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides

/**
 * Module which provides all database dependencies
 */
@Module(includes = [(ContextModule::class)])
object DaoModule {

    /**
     * Returns the database of the application.
     * @param context Context in which the application is running
     * @return the database of the application
     */
    @Provides
    @JvmStatic
    internal fun provideTransactionDatabase(context: Context): TransactionDatabase {
        return Room.databaseBuilder(context, TransactionDatabase::class.java, DATABASE_NAME).build()
    }

    /**
     * Provides the Transaction Dao object.
     * * @param transactionDb the database of the application
     * @return the dao object
     */
    @Provides
    @JvmStatic
    internal fun provideTransactionDao(transactionDb: TransactionDatabase): TransactionDao {
        return transactionDb.transactionDao()
    }
}