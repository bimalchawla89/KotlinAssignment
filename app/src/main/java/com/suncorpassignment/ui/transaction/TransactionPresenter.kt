package com.suncorpassignment.ui.transaction

import com.suncorpassignment.R
import com.suncorpassignment.network.ApiCallInterface
import com.suncorpassignment.ui.base.BasePresenter
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

    private var subscription: Disposable? = null

    override fun onViewCreated() {
        loadTransactions()
    }

    /**
     * Loads the posts from the API and presents them in the view when retrieved, or showss error if
     * any.
     */
    fun loadTransactions() {
        view.showLoading()
        subscription = apiCallInterface
                .getTransactions()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { transactionList -> view.updateTransactions(transactionList) },
                        { view.showError(R.string.unknown_error) }
                )
    }

    override fun onViewDestroyed() {
        subscription?.dispose()
    }
}