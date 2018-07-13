package com.suncorpassignment.injections.component

import com.suncorpassignment.injections.module.ContextModule
import com.suncorpassignment.injections.module.DaoModule
import com.suncorpassignment.injections.module.NetworkModule
import com.suncorpassignment.ui.base.BaseView
import com.suncorpassignment.ui.transaction.TransactionPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class), (DaoModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified TransactionPresenter.
     * @param transactionPresenter TransactionPresenter in which to inject the dependencies
     */
    fun inject(transactionPresenter: TransactionPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder
        fun daoModule(databaseModule: DaoModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}