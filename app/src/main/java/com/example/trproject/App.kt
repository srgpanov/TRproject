package com.example.trproject

import android.app.Application
import com.example.trproject.di.components.AppComponent
import com.example.trproject.di.components.DaggerAppComponent
import com.example.trproject.di.modules.AppModule

class App:Application() {
    lateinit var appComponent: AppComponent
        private set

    companion object {
        lateinit var instance: App
            private set
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent= DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
    }
}