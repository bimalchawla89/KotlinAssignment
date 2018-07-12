package com.suncorpassignment.ui.transaction

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
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
     * The list of posts of the adapter
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
        holder?.bind(transactions[position])
    }

    /**
     * Updates the list of posts of the adapter
     * @param transactions the new list of posts of the adapter
     */
    fun updateTransactions(transactions: List<Transaction>) {
        this.transactions = transactions
        notifyDataSetChanged()
    }

    /**
     * The ViewHolder of the adapter
     * @property binding the DataBinging object for Transaction item
     */
    class TransactionViewHolder(private val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root) {
        /**
         * Binds a transaction into the view
         */
        fun bind(transaction: Transaction) {
            binding.transaction = transaction
            binding.executePendingBindings()
        }
    }
}