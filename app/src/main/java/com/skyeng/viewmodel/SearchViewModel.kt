package com.skyeng.viewmodel

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.skyeng.App
import com.skyeng.api.IExternalApi
import com.skyeng.model.SearchResult
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel(private val app: App) : AndroidViewModel(app), LifecycleObserver {
    @Inject
    lateinit var externalApi: IExternalApi

    private var searchInput: String? = null
    private var searchDisposable: Disposable? = null
    var resultData: MutableLiveData<List<SearchResult>> = MutableLiveData()

    init {
        app.getApplicationComponent().inject(this)
    }

    @Bindable
    fun getSearchInput(): String? = searchInput

    fun setSearchInput(input: String?) {
        if (input != searchInput) {
            searchInput = input
            if (searchDisposable?.isDisposed == false) {
                searchDisposable?.dispose()
            }
            input?.let {
                searchDisposable = externalApi.searchWords(input, null, null)
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        Log.d("SKY", "result size ${result.size}")
                        resultData.postValue(result)
                    }, { throwable ->
                        Log.e("SKY", throwable.toString())
                    })
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        if (searchDisposable?.isDisposed == false) {
            searchDisposable?.dispose()
        }
    }
}