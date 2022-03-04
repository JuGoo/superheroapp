package com.ultimate.domain.factories

import com.ultimate.domain.usecases.*

class UseCaseFactory(
    private val repositoryFactory: RepositoryFactory
) {

    fun provideFetchHeroesUseCase() = FetchHeroesUseCase(
        fetchHeroesRepository = repositoryFactory.provideFetchHeroesRepository()
    )

    fun provideFetchHeroUseCase() = FetchHeroUseCase(
        fetchHeroRepository = repositoryFactory.provideFetchHeroRepository()
    )

    fun provideRemoveHeroToSquadUseCase() = RemoveHeroToSquadUseCase(
        squadRepository = repositoryFactory.provideSquadRepository()
    )

    fun provideAddHeroToSquadUseCase() = AddHeroToSquadUseCase(
        squadRepository = repositoryFactory.provideSquadRepository()
    )

    fun provideCheckHeroIsRecruitedUseCase() = CheckHeroIsRecruitedUseCase(
        squadRepository = repositoryFactory.provideSquadRepository()
    )

    fun provideFetchSquadUseCase() = FetchSquadUseCase(
        fetchHeroUseCase = provideFetchHeroUseCase(),
        squadRepository = repositoryFactory.provideSquadRepository()
    )

}
