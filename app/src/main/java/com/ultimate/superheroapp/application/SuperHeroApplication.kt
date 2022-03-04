package com.ultimate.superheroapp.application

import android.app.Application

class SuperHeroApplication: Application() {
    lateinit var appModule: AppModule

    override fun onCreate() {
        super.onCreate()
        appModule = AppModule(this)
    }
}