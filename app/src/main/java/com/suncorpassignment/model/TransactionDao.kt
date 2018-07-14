package com.suncorpassignment.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

/**
 * It provides query mechanism to interact with database tables.
 * Queries like insert and select from table
 */
@Dao
interface TransactionDao {

    @Query("SELECT * from transactionData")
    fun getAll(): List<Transaction>

    @Insert(onConflict = REPLACE)
    fun insert(vararg transactions: Transaction)

}