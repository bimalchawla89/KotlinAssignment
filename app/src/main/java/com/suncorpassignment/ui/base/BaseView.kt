package com.suncorpassignment.ui.base

import android.content.Context

/**
 * Base view any view must implement.
 */
interface BaseView {
    /**
     * Returns the Context in which the application is running.
     * @return the Context in which the application is running
     */
    fun getContext(): Context
}