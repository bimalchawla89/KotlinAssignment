package com.suncorpassignment.ui.transaction

import android.support.annotation.StringRes
import com.suncorpassignment.model.Transaction
import com.suncorpassignment.ui.base.BaseView

/**
 * Interface providing required method for a view displaying transactions
 */
interface TransactionView : BaseView {
    /**
     * Updates the previous transactions by the specified ones
     * @param transactions the list of transactions that will replace existing ones
     */
    fun updateTransactions(transactions: List<Transaction>)

    /**
     * Displays an error in the view
     * @param error the error to display in the view
     */
    fun showError(error: String)

    /**
     * Displays an error in the view
     * @param errorResId the resource id of the error to display in the view
     */
    fun showError(@StringRes errorResId: Int) {
        this.showError(getContext().getString(errorResId))
    }

    /**
     * Displays the loading indicator of the view
     */
    fun showLoading()

    /**
     * Hides the loading indicator of the view
     */
    fun hideLoading()
}