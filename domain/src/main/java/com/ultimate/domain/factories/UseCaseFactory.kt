package com.ultimate.domain.factories

import com.ultimate.domain.usecases.FetchHeroesUseCase

class UseCaseFactory(
    private val repositoryFactory: RepositoryFactory
) {

    fun provideFetchHeroesUseCase() = FetchHeroesUseCase(
        fetchHeroesRepository = repositoryFactory.provideFetchHeroesRepository()
    )
}
