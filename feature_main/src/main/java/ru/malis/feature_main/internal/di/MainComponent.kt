package ru.malis.feature_main.internal.di

import dagger.Component
import ru.malis.feature_main.api.MainDeps
import ru.malis.feature_main.api.MainFragment
import javax.inject.Scope

@Scope
annotation class MainScope

@[MainScope Component(
    dependencies = [MainDeps::class]
)]
internal interface MainComponent {

    @Component.Factory
    interface Factory {

        fun create(
            mainDeps: MainDeps
        ): MainComponent
    }

    fun inject(mainFragment: MainFragment)
}