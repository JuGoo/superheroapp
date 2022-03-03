package com.ultimate.superheroapp.application

import android.app.Application
import androidx.activity.ComponentActivity
import androidx.annotation.MainThread
import androidx.lifecycle.ViewModelProvider
import com.ultimate.presentation.factories.PresentationFactory

class AppModule(application: Application) {
    private val viewModelFactory: ViewModelProvider.Factory by lazy { createPresentationFactory() }

    private fun createPresentationFactory() = PresentationFactory()
}

@MainThread
internal inline fun <reified T> ComponentActivity.appModule(noinline block: AppModule.() -> T): Lazy<T> =
    lazy { (application as SuperHeroApplication).appModule.block() }