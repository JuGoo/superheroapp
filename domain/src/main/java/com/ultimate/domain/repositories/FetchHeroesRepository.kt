package com.ultimate.domain.repositories

import com.ultimate.domain.models.Hero

interface FetchHeroesRepository {

    suspend fun fetch(): List<Hero>
}