package com.skyeng

import android.app.Application
import com.skyeng.di.components.DaggerIApplicationComponent
import com.skyeng.di.components.IApplicationComponent
import com.skyeng.di.modules.RetrofitModule


class App : Application() {
    private lateinit var applicationComponent: IApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerIApplicationComponent.builder()
            .retrofitModule(RetrofitModule())
            .build()
    }

    fun getApplicationComponent(): IApplicationComponent {
        return applicationComponent
    }
}