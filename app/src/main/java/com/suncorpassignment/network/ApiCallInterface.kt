package com.suncorpassignment.network

import com.suncorpassignment.model.Transaction
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * The interface which provides methods to fetch result from webservice
 */
interface ApiCallInterface {
    /**
     * Get the list of the transactions from the API
     */
    @GET("/transactions")
    fun getTransactions(): Observable<List<Transaction>>
}