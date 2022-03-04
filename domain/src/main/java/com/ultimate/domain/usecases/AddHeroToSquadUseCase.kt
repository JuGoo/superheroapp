package com.ultimate.domain.usecases

import com.ultimate.domain.repositories.SquadRepository

class AddHeroToSquadUseCase(
    private val squadRepository: SquadRepository
) {
    suspend fun run(id: Int): Result<Unit> = runCatching {
        squadRepository.add(id)
    }
}