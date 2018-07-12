package com.suncorpassignment.model

/**
 * Model class which provides a model for transaction
 * @constructor Sets all properties of the particular transaction
 * @property id the unique identifier of the transaction
 * @property description the description of the transaction
 * @property amount the amount of the transaction
 * @property date the date of the transaction
 */
data class Transaction(val id: Int, val description: String, val amount: Double, val effectiveDate: String)