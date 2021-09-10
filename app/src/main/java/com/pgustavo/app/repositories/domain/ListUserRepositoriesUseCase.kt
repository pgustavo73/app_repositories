package com.pgustavo.app.repositories.domain

import com.pgustavo.app.repositories.core.UseCase
import com.pgustavo.app.repositories.data.module.Repo
import com.pgustavo.app.repositories.data.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow

class ListUserRepositoriesUseCase(
    private val repository: RepoRepository
) : UseCase<String, List<Repo>>() {

    override suspend fun execute(param: String): Flow<List<Repo>> {
        return repository.listRepositories(param)
    }
}