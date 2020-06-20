package com.skyeng.di.components

import com.skyeng.api.IExternalApi
import com.skyeng.di.modules.RetrofitModule
import com.skyeng.viewmodel.SearchViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RetrofitModule::class
])
interface IApplicationComponent {
    fun getExternalApi(): IExternalApi

    fun inject(vm: SearchViewModel)
}