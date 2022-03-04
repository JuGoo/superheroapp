package com.ultimate.domain.usecases

import com.ultimate.domain.models.Hero
import com.ultimate.domain.repositories.SquadRepository

class FetchSquadUseCase(
    private val fetchHeroUseCase: FetchHeroUseCase,
    private val squadRepository: SquadRepository
) {
    suspend fun run(): Result<List<Hero>> = runCatching {
        squadRepository.getAll().mapNotNull { fetchHero(it) }
    }

    private suspend fun fetchHero(id: Int): Hero? = fetchHeroUseCase.run(id).getOrNull()
}