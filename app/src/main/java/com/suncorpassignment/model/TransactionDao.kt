package com.suncorpassignment.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query

@Dao
interface TransactionDao {

    @Query("SELECT * from transactionData")
    fun getAll(): List<Transaction>

    @Insert(onConflict = REPLACE)
    fun insert(vararg transactions: Transaction)

}