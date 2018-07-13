package com.suncorpassignment.ui.transaction

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.suncorpassignment.R
import com.suncorpassignment.model.Transaction
import com.suncorpassignment.model.TransactionDao
import com.suncorpassignment.network.ApiCallInterface
import com.suncorpassignment.ui.base.BasePresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * The Presenter that will present the Transaction view.
 * @param transactionView the Transaction view to be presented by the presenter
 * @property ApiCallInterface the API interface implementation
 * @property subscription the subscription to the API call
 */
class TransactionPresenter(transactionView: TransactionView) : BasePresenter<TransactionView>(transactionView) {
    @Inject
    lateinit var apiCallInterface: ApiCallInterface
    @Inject
    lateinit var transactionDao: TransactionDao

    private var subscription: Disposable? = null

    private val mutableTransactionList: MutableLiveData<List<Transaction>> = MutableLiveData()

    override fun onViewCreated() {
        val transactionObserver = Observer<List<Transaction>> { transactionList ->
            if (transactionList != null) {
                view.updateTransactions(transactionList.sortedByDescending { it.effectiveDate })
            }
        }

        mutableTransactionList.observe(view, transactionObserver)
        loadTransactions()
    }

    /**
     * Loads the posts from the API and presents them in the view when retrieved, or showss error if
     * any.
     */
    fun loadTransactions() {
        view.showLoading()

        subscription = Observable.fromCallable({ transactionDao.getAll() })
                .flatMap { transactionList -> if (transactionList.isNotEmpty()) Observable.just(transactionList) else saveApiResponse() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { transactionList -> mutableTransactionList.value = transactionList },
                        { view.showError(R.string.unknown_error) }
                )

    }

    /**
     * Load the posts from the API and store them in the database.
     * @return an Observable for the list of Post retrieved from API
     */
    private fun saveApiResponse(): Observable<List<Transaction>> {
        return apiCallInterface.getTransactions()
                .flatMap { transactionList -> Observable.fromCallable({ transactionDao.insert(*transactionList.toTypedArray());transactionList }) }
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}