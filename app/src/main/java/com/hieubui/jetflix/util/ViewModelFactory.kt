package com.hieubui.jetflix.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory<V : ViewModel> @Inject constructor(
    private val provider: @JvmSuppressWildcards Provider<V>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = provider.get() as T
}
