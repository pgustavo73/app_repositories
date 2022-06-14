package com.pgustavo.app.repositories.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pgustavo.app.repositories.data.module.Repo
import com.pgustavo.app.repositories.data.module.User
import com.pgustavo.app.repositories.data.repositories.RepoRepositoryImpl
import com.pgustavo.app.repositories.domain.ListUserRepositoriesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel (
    private val listUserRepositoriesUseCase: ListUserRepositoriesUseCase,): ViewModel() {

    private lateinit var  mRepositoryImpl: RepoRepositoryImpl
    private val mutableLiveData : MutableLiveData<User> = MutableLiveData()

    private val _repos = MutableLiveData<State>()
    val repos: LiveData<State> = _repos

    fun getRepoList(user: String) {
            viewModelScope.launch {
                listUserRepositoriesUseCase(user)
                    .onStart {
                        _repos.postValue(State.Loading)
                    }
                    .catch {
                        _repos.postValue(State.Error(it))
                    }
                    .collect {
                        _repos.postValue(State.Success(it))
                    }
            }
        }

    fun buscarUsuario(user: String): LiveData<User> {
        if (:: mRepositoryImpl.isInitialized) {
        viewModelScope.launch(Dispatchers.IO){
        mRepositoryImpl.getUser(user)}}
        return mutableLiveData
    }

    sealed class State {
        object Loading : State()
        data class Success(val list: List<Repo>) : State()
        data class Error(val error: Throwable) : State()
    }
}