package com.pgustavo.app.repositories.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.bumptech.glide.Glide
import com.pgustavo.app.repositories.R
import com.pgustavo.app.repositories.core.createDialog
import com.pgustavo.app.repositories.core.createProgressDialog
import com.pgustavo.app.repositories.core.hideSoftKeyboard
import com.pgustavo.app.repositories.data.module.Repo
import com.pgustavo.app.repositories.data.module.User
import com.pgustavo.app.repositories.databinding.ActivityMainBinding
import com.pgustavo.app.repositories.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val dialog by lazy { createProgressDialog() }
    private val viewModel by viewModel<MainViewModel>()
    private val adapter by lazy { RepoListAdapter()}
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.rvRepos.adapter = adapter

        viewModel.repos.observe(this) {
            when (it) {
                MainViewModel.State.Loading -> dialog.show()
                is MainViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.list)
                }
           }
        }
    }

    private fun buscarUsuario(user: String) {
        Log.e("string", user)
        viewModel.buscarUsuario(user).observe(this, {result ->
            Log.e("usuario", result.name)
            bindOnView(result)
        })
    }

    private fun bindOnView(user: User) {
        binding.tvUser.text = user.name
        Log.e("usuario", user.name)
//        Glide.with(binding.cardeUser.context)
//            .load(user.avatarURL).into(binding.ivOwner)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val SearchView = (menu.findItem(R.id.action_search).actionView as SearchView)
        SearchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.getRepoList(it)
            buscarUsuario(it)}
        binding.root.hideSoftKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Log.e(TAG, "onQueryTextSubmit: $newText")
        return false
    }


    companion object {
        private const val  TAG = "TAG"
    }





}