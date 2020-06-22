package com.skyeng.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import com.skyeng.App
import com.skyeng.api.IExternalApi
import com.skyeng.model.MeaningDetails
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MeaningViewModel(app: Application) : AndroidViewModel(app), LifecycleObserver {
    @Inject
    lateinit var externalApi: IExternalApi

    var meaningData = MutableLiveData<MeaningDetails>()
    var meaningDisposable: Disposable? = null

    init {
        if (app is App) {
            app.getApplicationComponent().inject(this)
        }
    }

    fun load(id: Int) {
        if (meaningDisposable?.isDisposed == false) {
            meaningDisposable?.dispose()
        }

        meaningDisposable = externalApi.meanings(arrayOf(id))
            .subscribeOn(Schedulers.io())
            .subscribe({meanings ->
                if (meanings.isEmpty()) {
                    return@subscribe
                }

                meaningData.postValue(meanings[0])
            }, {throwable ->
                Log.e("SKY", throwable.toString())
            })
    }

    override fun onCleared() {
        super.onCleared()
    }
}