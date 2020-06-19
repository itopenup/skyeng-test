package com.skyeng.di.components

import com.skyeng.api.IExternalApi
import com.skyeng.di.modules.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RetrofitModule::class
])
interface IApplicationComponent {
    fun getExternalApi(): IExternalApi
}