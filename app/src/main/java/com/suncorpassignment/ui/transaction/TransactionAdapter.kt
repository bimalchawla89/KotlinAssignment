package com.suncorpassignment.ui.transaction

import android.app.AlertDialog
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.suncorpassignment.R
import com.suncorpassignment.databinding.ItemTransactionBinding
import com.suncorpassignment.model.Transaction

/**
 * Adapter for the list of the transactions
 * @property context Context in which the application is running
 */
class TransactionAdapter(private val context: Context) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    /**
     * The list of transactions of the adapter
     */
    private var transactions: List<Transaction> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding: ItemTransactionBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_transaction, parent, false)
        return TransactionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactions[position])
        //setting list item click listener
        holder.binding.transactionLayout.setOnClickListener({
            showDialog(transactions[position])
        })
    }

    /**
     * Updates the list of transactions of the adapter
     * @param transactions the new list of transactions of the adapter
     */
    fun updateTransactions(transactions: List<Transaction>) {
        this.transactions = transactions
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onClick(view: View, transaction: Transaction)
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for Transaction item
     */
    class TransactionViewHolder(val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a transaction into the view
         */
        fun bind(transaction: Transaction) {
            binding.transaction = transaction
            binding.executePendingBindings()
        }
    }

    /**
     * Showing alert dialog displaing full details of current item clicked
     * @property transaction the model object containing details for particular transaction
     */
    private fun showDialog(transaction: Transaction) {
        // Initialize a new instance of
        val builder = AlertDialog.Builder(context)

        // Set the alert dialog title
        builder.setTitle(Integer.toString(transaction.id))

        // Display a message on alert dialog
        builder.setMessage("Transaction of " + transaction.amount + " done on " + transaction.effectiveDate + " at " + transaction.description)

        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()

        // Display the alert dialog on app interface
        dialog.show()
    }
}