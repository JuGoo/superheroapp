package com.ultimate.domain.factories

import com.ultimate.domain.repositories.FetchHeroRepository
import com.ultimate.domain.repositories.FetchHeroesRepository
import com.ultimate.domain.repositories.SquadRepository

interface RepositoryFactory {

    fun provideFetchHeroesRepository(): FetchHeroesRepository
    fun provideFetchHeroRepository(): FetchHeroRepository
    fun provideSquadRepository(): SquadRepository
}
