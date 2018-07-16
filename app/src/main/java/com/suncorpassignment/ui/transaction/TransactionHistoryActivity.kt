package com.suncorpassignment.ui.transaction

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.suncorpassignment.R
import com.suncorpassignment.databinding.ActivityTransactionHistoryBinding
import com.suncorpassignment.model.Transaction
import com.suncorpassignment.ui.base.BaseActivity

/**
 * Activity displaying the list of transactions
 */
class TransactionHistoryActivity : BaseActivity<TransactionPresenter>(), TransactionView {
    /**
     * DataBinding instance
     */
    private lateinit var binding: ActivityTransactionHistoryBinding

    /**
     * The adapter for the list of transactions
     */
    private val transactionsAdapter = TransactionAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding view of recycler view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_history)
        binding.adapter = transactionsAdapter
        binding.layoutManager = LinearLayoutManager(this)

        presenter.onViewCreated()

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    //Updating/showing th list of transactions in list form through adapter
    override fun updateTransactions(transactions: List<Transaction>) {
        //summing all amounts present in list of transactions
        val totalPriceInList: Double = transactions.map { it.amount }.sum()
        //converting double amount value to float value to remove trailing zeros
        binding.total = totalPriceInList.toFloat()
        transactionsAdapter.updateTransactions(transactions)
    }

    override fun showError(error: String) {
        //showing the error if occur
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun instantiatePresenter(): TransactionPresenter {
        return TransactionPresenter(this)
    }
}