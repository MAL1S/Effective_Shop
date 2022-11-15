package ru.malis.feature_main.internal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.malis.feature_main.api.mainDepsProvider
import ru.malis.feature_main.internal.di.DaggerMainComponent
import ru.malis.feature_main.internal.di.MainComponent

internal class MainComponentViewModel(
    application: Application
): AndroidViewModel(application) {

    val mainComponent: MainComponent by lazy {
        DaggerMainComponent.factory().create(
            mainDeps = application.mainDepsProvider.mainDeps
        )
    }
}