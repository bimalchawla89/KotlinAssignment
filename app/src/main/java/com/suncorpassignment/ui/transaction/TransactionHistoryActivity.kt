package com.suncorpassignment.ui.transaction

import android.app.AlertDialog
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
 * Activity displaying the list of posts
 */
class TransactionHistoryActivity : BaseActivity<TransactionPresenter>(), TransactionView {
    /**
     * DataBinding instance
     */
    private lateinit var binding: ActivityTransactionHistoryBinding

    /**
     * The adapter for the list of posts
     */
    private val transactionsAdapter = TransactionAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_history)
        binding.adapter = transactionsAdapter
        binding.layoutManager = LinearLayoutManager(this)

        presenter.onViewCreated()

        transactionsAdapter.setOnItemClickListener(object : TransactionAdapter.OnItemClickListener {
            override fun onClick(view: View, transaction: Transaction) {

                // Initialize a new instance of
                val builder = AlertDialog.Builder(this@TransactionHistoryActivity)

                // Set the alert dialog title
                builder.setTitle(Integer.toString(transaction.id))

                // Display a message on alert dialog
                builder.setMessage("Transaction of " + transaction.amount + " done on " + transaction.effectiveDate + " at " + transaction.description)

                // Finally, make the alert dialog using builder
                val dialog: AlertDialog = builder.create()

                // Display the alert dialog on app interface
                dialog.show()

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun updateTransactions(transactions: List<Transaction>) {
        //summing all amounts present in list of transactions
        val totalPriceInList: Double = transactions.map { it.amount }.sum()
        // println("sum(): " + totalPriceInList.toString()  + totalPriceInList.toFloat())
        //converting double amount value to float value to remove trailing zeros
        binding.total = totalPriceInList.toFloat();
        transactionsAdapter.updateTransactions(transactions)
    }

    override fun showError(error: String) {
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