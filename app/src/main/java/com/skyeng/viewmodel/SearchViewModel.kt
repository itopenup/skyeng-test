package com.skyeng.viewmodel

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import com.skyeng.App
import com.skyeng.api.IExternalApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel(private val app: App) : AndroidViewModel(app) {
    @Inject
    lateinit var externalApi: IExternalApi

    private var searchInput: String? = null

    init {
        app.getApplicationComponent().inject(this)
    }

    @Bindable
    fun getSearchInput(): String? = searchInput

    fun setSearchInput(input: String?) {
        if (input != searchInput) {
            searchInput = input
            input?.let {
                externalApi.searchWords(input, null, null)
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        Log.d("SKY", "result size ${result.size}")
                    }, { throwable ->
                        Log.e("SKY", throwable.toString())
                    })
            }
        }
    }
}