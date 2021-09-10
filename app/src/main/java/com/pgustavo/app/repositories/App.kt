package com.pgustavo.app.repositories

import android.app.Application
import com.pgustavo.app.repositories.data.di.DataModule
import com.pgustavo.app.repositories.domain.di.DomainModule
import com.pgustavo.app.repositories.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()

    }
}