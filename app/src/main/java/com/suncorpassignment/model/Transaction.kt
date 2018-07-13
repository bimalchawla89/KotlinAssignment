package com.suncorpassignment.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Model class which provides a model for transaction
 * @constructor Sets all properties of the particular transaction
 * @property id the unique identifier of the transaction
 * @property description the description of the transaction
 * @property amount the amount of the transaction
 * @property effectiveDate the date of the transaction
 */
@Entity(tableName = "transactionData")
data class Transaction(@ColumnInfo(name = "id")
                       @PrimaryKey(autoGenerate = true) val id: Int,
                       @ColumnInfo(name = "transaction_description") val description: String,
                       @ColumnInfo(name = "transaction_amount") val amount: Double,
                       @ColumnInfo(name = "transaction_date") val effectiveDate: String)
