package com.pgustavo.app.repositories.data.services

import com.pgustavo.app.repositories.data.module.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    suspend fun listRepositories(@Path("user") user: String) : List<Repo>
}