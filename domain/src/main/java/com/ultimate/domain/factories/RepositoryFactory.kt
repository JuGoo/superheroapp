package com.ultimate.domain.factories

import com.ultimate.domain.repositories.FetchHeroRepository
import com.ultimate.domain.repositories.FetchHeroesRepository

interface RepositoryFactory {

    fun provideFetchHeroesRepository(): FetchHeroesRepository
    fun provideFetchHeroRepository(): FetchHeroRepository
}
