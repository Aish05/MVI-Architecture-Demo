package aish.android.mvisample.ui

import aish.android.mvisample.util.DataState

interface DataStateListener {

    fun onDataStateChange(dataState: DataState<*>?)
}