package com.pgustavo.app.repositories.presentation.di

import com.pgustavo.app.repositories.domain.ListUserRepositoriesUseCase
import com.pgustavo.app.repositories.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {
        return module {
            viewModel { MainViewModel(get())}
        }
    }
}