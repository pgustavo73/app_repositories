package com.pgustavo.app.repositories.data.services

import com.pgustavo.app.repositories.data.module.Repo
import com.pgustavo.app.repositories.data.module.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun listRepositories(@Path("user") user: String) : List<Repo>

    @GET("users/{user}")
    suspend fun getUser(@Path("user") user: String): Response<User>

}