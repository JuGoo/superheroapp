package com.ultimate.domain.usecases

import com.ultimate.domain.models.Hero
import com.ultimate.domain.repositories.FetchHeroRepository

class FetchHeroUseCase(
    private val fetchHeroRepository: FetchHeroRepository
) {
    suspend fun run(id: Int): Result<Hero> = runCatching {
        fetchHeroRepository.fetch(id)
    }
}