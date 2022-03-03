package com.ultimate.domain.usecases

import com.ultimate.domain.models.Hero
import com.ultimate.domain.repositories.FetchHeroesRepository

class FetchHeroesUseCase(
    private val fetchHeroesRepository: FetchHeroesRepository
) {
    suspend fun run(): Result<List<Hero>> = runCatching {
        val result = fetchHeroes()
        result
    }

    private suspend fun fetchHeroes() = fetchHeroesRepository.fetch()
}