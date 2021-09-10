package com.pgustavo.app.repositories.data.repositories

import kotlinx.coroutines.flow.Flow
import com.pgustavo.app.repositories.data.module.Repo

interface RepoRepository {
    suspend fun listRepositories(user: String): Flow<List<Repo>>
}