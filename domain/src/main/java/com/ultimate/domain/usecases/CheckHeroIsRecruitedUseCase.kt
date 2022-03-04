package com.ultimate.domain.usecases

import com.ultimate.domain.repositories.SquadRepository

class CheckHeroIsRecruitedUseCase(
    private val squadRepository: SquadRepository
) {
    suspend fun run(id: Int): Result<Boolean> = runCatching {
        squadRepository.contains(id)
    }
}