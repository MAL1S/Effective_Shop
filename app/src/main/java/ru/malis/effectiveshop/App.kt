package ru.malis.effectiveshop

import android.app.Application
import ru.malis.effectiveshop.di.AppComponent
import ru.malis.effectiveshop.di.DaggerAppComponent
import ru.malis.feature_main.api.MainDeps
import ru.malis.feature_main.api.MainDepsProvider

class App: Application(), MainDepsProvider {

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }

    internal val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory()
            .create(
                context = this
            )
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    override val mainDeps: MainDeps = appComponent
}